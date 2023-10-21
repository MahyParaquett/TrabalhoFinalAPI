package br.com.api.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.dto.ItemPedidoDTO;
import br.com.api.ecommerce.dto.RelatorioPedidosDto;
import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.repositories.ItemPedidoRepository;
import br.com.api.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	ItemPedidoRepository itempedidoRepo;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepo.findById(id).orElse(null);
	}

	public RelatorioPedidosDto getPedidoResumidoPorId(Integer id) {

		Pedido pedido = pedidoRepo.findById(id).orElse(null);

		if (pedido == null) {
			return null;
		}

		RelatorioPedidosDto relatorioPedidoDTO = new RelatorioPedidosDto();

		relatorioPedidoDTO.setIdPedido(pedido.getIdPedido());
		relatorioPedidoDTO.setDataPedido(pedido.getDataPedido());
		relatorioPedidoDTO.setValorTotal(pedido.getValorTotal());

		List<ItemPedido> itensPedidos = itempedidoRepo.findAll();
		List<ItemPedidoDTO> itensDTO = new ArrayList<>();
		for (ItemPedido itemPedido : itensPedidos) {
			ItemPedidoDTO itemDTO = new ItemPedidoDTO();
			Produto produto = new Produto();
			
			
			// Acesse o produto a partir do ItemPedido

		
			itemDTO.setCodigoProduto(produto.getIdProduto());
			itemDTO.setNomeProduto(produto.getNome());
			itemDTO.setPrecoVenda(produto.getValorUnitario());
			itemDTO.setQuantidade(itemPedido.getQuantidade());
			itemDTO.setValorBruto(itemPedido.getValorBruto());
			itemDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
			itemDTO.setValorLiquido(itemPedido.getValorLiquido());

			// Adiciona DTO a relação de itens
			itensDTO.add(itemDTO);
		}
		// Atribui a lista de itens ao objeto de relatório
		relatorioPedidoDTO.setItens(itensDTO);

		return relatorioPedidoDTO;
	}

	public Pedido salvarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Pedido atualizarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null)
			return false;

		Pedido pedidoExistente = buscarPedidoPorId(pedido.getIdPedido());

		if (pedidoExistente == null)
			return false;

		pedidoRepo.delete(pedido);

		Pedido pedidoContinuaExistindo = buscarPedidoPorId(pedido.getIdPedido());

		if (pedidoContinuaExistindo == null)
			return true;

		return false;
	}

}
