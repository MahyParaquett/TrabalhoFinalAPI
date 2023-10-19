package br.com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer>{

}
