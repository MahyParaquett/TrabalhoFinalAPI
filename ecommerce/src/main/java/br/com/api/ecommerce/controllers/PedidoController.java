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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ecommerce.dto.RelatorioPedidosDto;
import br.com.api.ecommerce.entities.Pedido;
import br.com.api.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);

		if (pedido == null)
			return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@PostMapping("/calcularValores/{id}")
	public ResponseEntity<String> calcularValores(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		if (pedido == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
		}

		pedidoService.calcularValores(pedido);
		return ResponseEntity.ok("Valores calculados com sucesso");
	}

	@PostMapping("/calcularTotal/{id}")
	public ResponseEntity<String> calcularTotal(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		if (pedido == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
		}

		pedidoService.calcularTotal(pedido);
		return ResponseEntity.ok("Total calculado com sucesso");
	}

	@GetMapping("/RelatorioPedidos/{id}")
	public ResponseEntity<RelatorioPedidosDto> getPedidoResumidoPorId(@PathVariable Integer id) {
		RelatorioPedidosDto relatoriopedidosdto = pedidoService.getPedidoResumidoPorId(id);
		if (relatoriopedidosdto == null)
			return new ResponseEntity<>(relatoriopedidosdto, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(relatoriopedidosdto, HttpStatus.OK);
	}

	@GetMapping("/porid")
	public ResponseEntity<Pedido> buscarPedidoPorId(@RequestParam Integer id) {
		return new ResponseEntity<>(pedidoService.buscarPedidoPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.salvarPedido(pedido), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.atualizarPedido(pedido), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if (pedidoService.deletarPedido(pedido))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);

		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
