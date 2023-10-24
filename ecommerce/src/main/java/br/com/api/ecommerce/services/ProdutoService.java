package br.com.api.ecommerce.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepo;

	public List<Produto> listarProdutos() {
		return produtoRepo.findAll();
	}

	public Produto buscarProdutoPorId(Integer id) {
		return produtoRepo.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Produto" + id)); 
	}

	public Produto salvarProduto(Produto produto) {
		// Verifica se já existe um produto com a mesma descrição
		if (produtoRepo.existsByDescricao(produto.getDescricao())) {
			throw new IllegalArgumentException("Já existe um produto com essa descrição.");
		}

		return produtoRepo.save(produto);
	}

	public Produto atualizarProduto(Produto produto) {
		return produtoRepo.save(produto);
	}

	public Boolean deletarProduto(Produto produto) {
		if (produto == null)
			return false;

		Produto produtoExistente = buscarProdutoPorId(produto.getIdProduto());

		if (produtoExistente == null)
			return false;

		produtoRepo.delete(produto);

		Produto produtoContinuaExistindo = buscarProdutoPorId(produto.getIdProduto());

		if (produtoContinuaExistindo == null)
			return true;

		return false;
	}
	
	// Adicionando imagem:
	/*
	public Produto salvarComImagem(String strProduto, MultipartFile arqImg) 
			throws JsonMappingException, JsonProcessingException {
		Produto produto = new Produto();
		
		try {
			ObjectMapper objMp = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			produto = objMp.readValue(strProduto, Produto.class);
			produto.setImagem(arqImg.getBytes());
		} catch (IOException e) {
			System.out.println("Erro na conversão da string");
		}
		
		return produtoRepo.save(produto);
	}*/
	
	public Produto addImagem(Integer id, MultipartFile arqImg) throws IOException {
		Produto produto = produtoRepo.findById(id).orElse(null);
	    if (produto != null) {
	        String emBase64 = Base64.getEncoder().encodeToString(arqImg.getBytes());
	    	produto.setImagem(emBase64);
	        return produtoRepo.save(produto);
	    }
	    return null;
	}

}
