package br.com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.entities.Cliente;
import br.com.api.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	

	@Autowired 
	ClienteRepository clienteRepo;

	public List<Cliente> listarClientes() {
		return clienteRepo.findAll();
	}

	public Cliente buscarClientePorId(Integer id) {
		return clienteRepo.findById(id).orElse(null); 
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public Cliente atualizarCliente (Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public Boolean deletarCliente(Cliente cliente) {
		if(cliente == null)
			return false;
		
		Cliente clienteExistente = buscarClientePorId(cliente.getIdCliente());
		
		if(clienteExistente == null)
			return false;
		
		clienteRepo.delete(cliente);
		
		Cliente clienteContinuaExistindo = 
				buscarClientePorId(cliente.getIdCliente());
		
		if(clienteContinuaExistindo == null)
			return true;

		return false;	
	}

}



