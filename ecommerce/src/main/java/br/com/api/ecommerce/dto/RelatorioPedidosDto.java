package br.com.api.ecommerce.dto;

import java.util.Date;
import java.util.List;

public class RelatorioPedidosDto {

	private Integer idPedido;
	private Date dataPedido;
	private Double valorTotal;
	private List<ItemPedidoDTO> itens;

	public RelatorioPedidosDto() {

	}

	public RelatorioPedidosDto(Integer idPedido, Date dataPedido, Double valorTotal, List<ItemPedidoDTO> itens) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.itens = itens;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "RelatorioPedidosDto [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", valorTotal=" + valorTotal
				+ ", itens=" + itens + "]";
	}

}
