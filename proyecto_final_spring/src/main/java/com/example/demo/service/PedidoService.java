package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {

	private List<Pedido> pedidos = new ArrayList<>();

	@Autowired
	private UsuarioService serviceUser;
	
	private long pedidoId = 0;
	
	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public long getPedidoId() {
		return pedidoId;
	}

	/**
	 * Cuando se cree un pedido llamaremos a este método y se seteará automáticamente.
	 */
	public void setPedidoId() {
		pedidoId++;
	}

	public Pedido add(Pedido e) {
		pedidos.add(e);
		return e;
	}

	public List<Pedido> findAll() {
		return pedidos;
	}

	@Autowired
	private HttpSession sesion;

	/**
	 * Genero unos datos estáticos para poder probar la aplicación.
	 * Realmente lo estoy añadiendo en el constructor de la clase Usuario, en su lista de pedidos.
	 */
	@PostConstruct
	public void init() {
		pedidos.addAll(Arrays.asList(new Pedido("Calle Luna, 45, Fantasyland, La Luna"),
				(new Pedido("Av. Constitucón, 73, Rojo, Redland"))));
	}

	/**
	 * Método para poder encontrar un pedido por su id. Consultaremos los del
	 * usuario logueado. Los pedidos generados arriba son para hacer pruebas.
	 * 
	 * @param id
	 * @return pedido concreto o null en caso de no encontrarlo.
	 */
	public Pedido findById(long id) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		boolean encontrado = false;
		Pedido pedidoEncontrado = null;
		int i = 0;
		while (!encontrado && i < pedidos.size()) {
			if (usuario.getPedidos().get(i).getId() == id) {
				pedidoEncontrado = usuario.getPedidos().get(i);
			} else {
				i++;
			}
		}
		return pedidoEncontrado;
	}

	/**
	 * No comprobamos si el usuario existe puesto que ya se está controlando la
	 * sesión y, además, si ha llegado hasta aquí, debe estar registrado y tener una
	 * lista de pedidos, aunque esté vacía. Le añado los pedidos al usuario (ya que
	 * son datos estáticos, para que tenga algunos de base y comprobar el
	 * resultado).
	 * 
	 * @param usuario
	 * @return lista de pedidos del usuario
	 */
	public List<Pedido> encuentraPedidosDeUsuario() {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		return usuario.getPedidos();
	}

	/**
	 * Método para crear un pedido para el usuario logueado. Añade un pedido a la lista de pedidos del usuario.
	 */
	public void creaPedido(Pedido pedido) {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		usuario.getPedidos().add(pedido);
	}
	
	

}
