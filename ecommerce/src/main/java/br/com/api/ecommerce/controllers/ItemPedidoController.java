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

import br.com.api.ecommerce.entities.ItemPedido;
import br.com.api.ecommerce.services.ItemPedidoService;

@RestController
@RequestMapping("/itenspedidos")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itempedidoService;
	
	@GetMapping
	public ResponseEntity <List<ItemPedido>> listarItemPedidos() {
		return new ResponseEntity<> (itempedidoService.listarItemPedidos(),
				HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Integer id) {
		ItemPedido itempedido = itempedidoService.buscarItemPedidoPorId(id);
		
		if(itempedido == null)
		return new  ResponseEntity<>(itempedido, HttpStatus.NOT_FOUND);
	
		else
			return new ResponseEntity<>(itempedido, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<ItemPedido> salvarItemPedido(@RequestBody ItemPedido itempedido) {
		return new ResponseEntity<>(itempedidoService.salvarItemPedido(itempedido), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity <ItemPedido> atualizar(@RequestBody ItemPedido itempedido) {
		return new ResponseEntity<>(itempedidoService.atualizarItemPedido(itempedido), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarItemPedido(@RequestBody ItemPedido itempedido) {
		if(itempedidoService.deletarItemPedido(itempedido))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);	

	else 
		return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}


