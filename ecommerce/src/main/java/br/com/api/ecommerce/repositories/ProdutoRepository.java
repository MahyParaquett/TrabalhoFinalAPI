package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
