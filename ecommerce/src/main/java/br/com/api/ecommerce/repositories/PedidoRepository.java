package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

}
