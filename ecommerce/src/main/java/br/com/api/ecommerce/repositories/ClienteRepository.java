package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

}
