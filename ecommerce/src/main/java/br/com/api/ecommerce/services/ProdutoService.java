package br.com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.repositories.ProdutoRepository;

public class ProdutoService {


	@Autowired 
	ProdutoRepository produtoRepo;

	public List<Produto> listarProdutos() {
		return produtoRepo.findAll();
	}

	public Produto buscarProdutoPorId(Integer id) {
		return produtoRepo.findById(id).orElse(null); 
	}

	public Produto salvarProduto(Produto produto) {
		return produtoRepo.save(produto);
	}

	public Produto atualizarProduto (Produto produto) {
		return produtoRepo.save(produto);
	}

	public Boolean deletarProduto(Produto produto) {
		if(produto == null)
			return false;
		
		Produto produtoExistente = buscarProdutoPorId(produto.getIdProduto());
		
		if(produtoExistente == null)
			return false;
		
		produtoRepo.delete(produto);
		
		Produto produtoContinuaExistindo = 
				buscarProdutoPorId(produto.getIdProduto());
		
		if(produtoContinuaExistindo == null)
			return true;

		return false;	
	}

}


	

