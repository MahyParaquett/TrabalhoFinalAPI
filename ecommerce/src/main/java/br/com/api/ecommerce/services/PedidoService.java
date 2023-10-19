package br.com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.repositories.PedidoRepository;

public class PedidoService {


	@Autowired 
	PedidoRepository pedidoRepo;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepo.findById(id).orElse(null); 
	}

	public Pedido salvarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Pedido atualizarPedido (Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if(pedido == null)
			return false;
		
		Pedido pedidoExistente = buscarPedidoPorId(pedido.getIdPedido());
		
		if(pedidoExistente == null)
			return false;
		
		pedidoRepo.delete(pedido);
		
		Pedido pedidoContinuaExistindo = 
				buscarPedidoPorId(pedido.getIdPedido());
		
		if(pedidoContinuaExistindo == null)
			return true;

		return false;	
	}

}

