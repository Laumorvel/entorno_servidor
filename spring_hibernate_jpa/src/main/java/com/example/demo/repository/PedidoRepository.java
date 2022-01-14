package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	/**
	 * Encuantra el pedido concreto
	 * @param id
	 * @return
	 */
	public List<Pedido> findByUsuarioId(long id);
}
