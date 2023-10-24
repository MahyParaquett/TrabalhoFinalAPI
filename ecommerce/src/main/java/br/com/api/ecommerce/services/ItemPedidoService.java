package br.com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {


	@Autowired 
	ItemPedidoRepository itemPedidoRepo;

	public List<ItemPedido> listarItemPedidos() {
		return itemPedidoRepo.findAll();
	}

	public ItemPedido buscarItemPedidoPorId(Integer id) {
		return itemPedidoRepo.findById(id).orElse(null); 
	}

	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		 double valorBruto = itemPedido.getPrecoVenda() * itemPedido.getQuantidade();
		    double valorLiquido = valorBruto - (valorBruto * (itemPedido.getPercentualDesconto() / 100));
		    itemPedido.setValorBruto(valorBruto);
		    itemPedido.setValorLiquido(valorLiquido);

		    // Salvando o ItemPedido
		    ItemPedido itemSalvo = itemPedidoRepo.save(itemPedido);

		    // Calculando e atualizando o valor total do pedido
		    Pedido pedido = itemSalvo.getPedido();
		    List<ItemPedido> itensPedido = pedido.getItensPedidos();
		    double valorTotal = itensPedido.stream().mapToDouble(ItemPedido::getValorLiquido).sum();
		    pedido.setValorTotal(valorTotal);

		    // Salvando o Pedido atualizado
		   

		    return itemSalvo;
	}

	public ItemPedido atualizarItemPedido (ItemPedido itemPedido) {
		return itemPedidoRepo.save(itemPedido);
	}

	public Boolean deletarItemPedido(ItemPedido itemPedido) {
		if(itemPedido == null)
			return false;
		
		ItemPedido itemPedidoExistente = buscarItemPedidoPorId(itemPedido.getIdItemPedido());
		
		if(itemPedidoExistente == null)
			return false;
		
		itemPedidoRepo.delete(itemPedido);
		
		ItemPedido itemPedidoContinuaExistindo = 
				buscarItemPedidoPorId(itemPedido.getIdItemPedido());
		
		if(itemPedidoContinuaExistindo == null)
			return true;

		return false;	
	}

}


	

