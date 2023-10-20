package br.com.api.ecommerce.dto;

import java.util.Date;

public class RelatorioPedidosDto {

	private Integer idPedido;
	private Date dataPedido;
	private Double codigoProduto;
	private Double precoVenda;
	private Integer quantidade;
	private Double valorBruto;
	private Double percentualDesconto;
	private Double valorLiquido;
	
	public RelatorioPedidosDto() {
		
	}

	public RelatorioPedidosDto(Integer idPedido, Date dataPedido, Double codigoProduto, Double precoVenda,
			Integer quantidade, Double valorBruto, Double percentualDesconto, Double valorLiquido) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.codigoProduto = codigoProduto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.valorBruto = valorBruto;
		this.percentualDesconto = percentualDesconto;
		this.valorLiquido = valorLiquido;
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

	public Double getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Double codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	
	
	
	
}
