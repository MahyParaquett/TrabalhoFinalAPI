package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{

}
