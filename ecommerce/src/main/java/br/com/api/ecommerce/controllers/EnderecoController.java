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

import br.com.api.ecommerce.entities.Endereco;
import br.com.api.ecommerce.services.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity <List<Endereco>> listarEnderecos() {
		return new ResponseEntity<> (enderecoService.listarEnderecos(),
				HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarPorId(@Valid @PathVariable Integer id) {
		Endereco endereco = enderecoService.buscarEnderecoPorId(id);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}
	
		
	@PostMapping
	public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
	}
	
	
	@PutMapping
	public ResponseEntity <Endereco> atualizar(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.atualizarEndereco(endereco), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarEndereco(@RequestBody Endereco endereco) {
		if(enderecoService.deletarEndereco(endereco))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);	

	else 
		return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}


