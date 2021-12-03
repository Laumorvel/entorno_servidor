package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pedido;

@Service
public class PedidoService {

	private List<Pedido> pedidos = new ArrayList<>();

	public Pedido add(Pedido e) {
		pedidos.add(e);
		return e;
	}

	public List<Pedido> findAll() {
		return pedidos;
	}

	/**
	 * Genero unos datos estáticos para poder probar la aplicación.
	 */
	@PostConstruct
	public void init() {
		pedidos.addAll(Arrays.asList(new Pedido("Calle Luna, 45, Fantasyland, La Luna"),
				(new Pedido("Av. Constitucón, 73, Rojo, Redland"))));
	}
	
	/**
	 * Método para poder encontrar un pedido por su id.
	 * @param id
	 * @return pedido concreto o null en caso de no encontrarlo.
	 */
	public Pedido findById(long id) {
		boolean encontrado = false;
		Pedido pedidoEncontrado = null;
		int i = 0;
		while(!encontrado && i < pedidos.size()) {
			if(pedidos.get(i).getId() == id) {
				pedidoEncontrado = pedidos.get(i);
			}else {
				i++;
			}
		}
		return pedidoEncontrado;
	}

}
