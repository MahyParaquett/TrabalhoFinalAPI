package br.com.api.ecommerce.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ecommerce.entities.Produto;
import br.com.api.ecommerce.services.ProdutoService;
import jakarta.validation.Valid;

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
	public ResponseEntity<Produto> buscarPorId(@Valid @PathVariable Integer id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
		
	@PostMapping
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.CREATED);
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


