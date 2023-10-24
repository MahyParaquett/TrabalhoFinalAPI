package br.com.api.ecommerce.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.ecommerce.dto.ItemPedidoDTO;
import br.com.api.ecommerce.dto.RelatorioPedidosDto;
import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.repositories.ItemPedidoRepository;
import br.com.api.ecommerce.repositories.PedidoRepository;
import br.com.api.ecommerce.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;

	@Autowired
	ItemPedidoRepository itempedidoRepo;

	@Autowired
	ProdutoRepository produtoRepo;

	@Autowired
	EmailService emailService;
	
	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepo.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Pedido" + id));
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
			// Produto produto = new Produto();

			Produto produto = produtoRepo.findById(itemPedido.getProduto().getIdProduto()).orElse(null);

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
		
        // Vai obter a data atual
        LocalDate dataAtual = LocalDate.now().minusDays(1);

        // Vai converter a data do pedido para LocalDate (supondo que a dataPedido seja
        // do tipo LocalDate)
        LocalDate dataDoPedido = pedido.getDataPedido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Compara as datas:
        if (dataDoPedido.isBefore(dataAtual)) {
            throw new IllegalArgumentException("A data do pedido não pode ser retroativa.");
        }

        // Vai calcular o valor total, se tem desconto ou não e deixar essa informação
        // salva no banco
        calcularValores(pedido);
        calcularTotal(pedido);

        Pedido novoPedido = pedidoRepo.save(pedido);
        RelatorioPedidosDto relatorioDto = getPedidoResumidoPorId(pedido.getIdPedido());
        
        
        emailService.enviarEmail("cintiaazevedocastrogall@gmail.com", "Novo pedido efetuado!", relatorioDto.toString());	
        return novoPedido;
      
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
