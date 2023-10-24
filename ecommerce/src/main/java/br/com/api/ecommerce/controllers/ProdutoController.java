package br.com.api.ecommerce.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity <List<Produto>> listarProdutos() {
		return new ResponseEntity<> (produtoService.listarProdutos(),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		
		if(produto == null)
		return new  ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
	
		else
			return new ResponseEntity<>(produto, HttpStatus.OK);
	}
		
	@PostMapping
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.CREATED);
	}
	
	
	// Adicionar imagem:
	
	// @PostMapping("/{id}/add-image")
	// Modelo do body: 'form-data'
	// Adicionar um campo 'file' para fazer o upload de imagens
	
	/*
	@PostMapping("/comimagem")
	public ResponseEntity<Produto> salvarComImagem(@RequestPart("prod") String strProduto, 
			@RequestPart("img") MultipartFile arqImg) {
		return new ResponseEntity<>(produtoService.salvarComImagem(strProduto, arqImg), HttpStatus.CREATED);
	}*/
	
	@PostMapping("/{id}/add-image")
    public ResponseEntity<String> addImagem(@PathVariable Integer id, @RequestPart("imagem") MultipartFile arqImg) {
		try {
			Produto atualizado = produtoService.addImagem(id, arqImg);
			if (atualizado != null) {
				return new ResponseEntity<>("Atualizado com Sucesso.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
			}
		} catch (IllegalStateException | IOException e) {
			return new ResponseEntity<>("Erro ao tentar adicionar uma imagem", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	
	@PutMapping
	public ResponseEntity <Produto> atualizar(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.atualizarProduto(produto), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarProduto(@RequestBody Produto produto) {
		if(produtoService.deletarProduto(produto))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}