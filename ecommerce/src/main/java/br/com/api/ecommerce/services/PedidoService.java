package br.com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.dto.RelatorioPedidosDto;
import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepo.findById(id).orElse(null);
	}

	public RelatorioPedidosDto getPedidoResumidoPorId(Integer id) {

		Pedido pedido = pedidoRepo.findById(id).orElse(null);
		RelatorioPedidosDto relatoriopedidosDto = new RelatorioPedidosDto();

		relatoriopedidosDto.setIdPedido(pedido.getIdPedido());
		relatoriopedidosDto.setDataPedido(pedido.getDataPedido());
		
		return relatoriopedidosDto;
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
	public void calcularValores(Pedido pedido) {
	    List<ItemPedido> itens = pedido.getItensPedidos(); 

	    for (ItemPedido item : itens) {
	        double valorBruto = item.getPrecoVenda() * item.getQuantidade(); 
	        double valorLiquido = valorBruto - (valorBruto * (item.getPercentualDesconto() / 100)); 
	        item.setValorBruto(valorBruto); 
	        item.setValorLiquido(valorLiquido); 
	    }
	}

	public void calcularTotal(Pedido pedido) {
	    List<ItemPedido> itens = pedido.getItensPedidos(); 

	    double total = 0.0; 
	    for (ItemPedido item : itens) {
	        total += item.getValorLiquido(); 
	    }

	    pedido.setValorTotal(total); 
	}


		public PedidoRepository getPedidoRepo() {
			return pedidoRepo;
		}

		public void setPedidoRepo(PedidoRepository pedidoRepo) {
			this.pedidoRepo = pedidoRepo;
		}


}
