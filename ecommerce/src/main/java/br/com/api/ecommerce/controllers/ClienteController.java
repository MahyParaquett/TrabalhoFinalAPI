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

import br.com.api.ecommerce.entities.Cliente;
import br.com.api.ecommerce.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> listarClientes() {
		return new ResponseEntity<> (clienteService.listarClientes(),
				HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@Valid @PathVariable Integer id) {
		Cliente cliente = clienteService.buscarClientePorId(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
		
	@PostMapping
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity <Cliente> atualizar(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.atualizarCliente(cliente), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarCliente(@RequestBody Cliente cliente) {
		if(clienteService.deletarCliente(cliente))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);	

	else 
		return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
