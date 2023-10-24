package br.com.api.ecommerce.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {


	@Autowired 
	ItemPedidoRepository itemPedidoRepo;

		
	public List<ItemPedido> listarItemPedidos() {
		return itemPedidoRepo.findAll();
	}

	public ItemPedido buscarItemPedidoPorId(Integer id) {
		return itemPedidoRepo.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Item Pedido" + id)); 
	}

	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepo.save(itemPedido);
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


	

