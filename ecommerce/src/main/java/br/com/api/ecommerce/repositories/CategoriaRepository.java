package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
