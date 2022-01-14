package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class PedidoService {

	private List<Pedido> pedidos = new ArrayList<>();

	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
//		@Query("select e from usuario e where (e.id) == :userId") 
//		Usuario findUserById(long userId);

	/**
	 * utilizo esta propiedad para que pueda reconocer el pedido nuevo que se ha
	 * reliado y poder obtenerlo más fácilmente.
	 */
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
	 * Cuando se cree un pedido llamaremos a este método y se seteará
	 * automáticamente.
	 */
	public void setPedidoId(long pedidoRealizadoId) {
		pedidoId = pedidoRealizadoId;
	}

	public void add(Pedido e) {
		repositorio.save(e);
	}

	public List<Pedido> findAll() {
		return repositorio.findAll();
	}

//	/**
//	 * Genero unos datos estáticos para poder probar la aplicación. Realmente lo
//	 * estoy añadiendo en el constructor de la clase Usuario, en su lista de
//	 * pedidos.
//	 */
//	@PostConstruct
//	public void init() {
//		pedidos.addAll(Arrays.asList(new Pedido("Calle Luna, 45, Fantasyland, La Luna"),
//				(new Pedido("Av. Constitucón, 73, Rojo, Redland"))));
//	}

//	/**
//	 * Método para poder encontrar un pedido por su id. Consultaremos los del
//	 * usuario logueado. Los pedidos generados arriba son para hacer pruebas.
//	 * 
//	 * @param id
//	 * @return pedido concreto o null en caso de no encontrarlo.
//	 */
//	public Pedido findById(long id) {
//		Usuario usuario = usuarioRepo.getById(serviceUser.getUserId());
//		boolean encontrado = false;
//		Pedido pedidoEncontrado = null;
//		int i = 0;
//		while (!encontrado && i < pedidos.size()) {
//			if (usuario.getPedidos().get(i).getId() == id) {
//				pedidoEncontrado = usuario.getPedidos().get(i);
//			} else {
//				i++;
//			}
//		}
//		return pedidoEncontrado;
//	}

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
	public List<Pedido> encuentraPedidosDeUsuario(long userId) {
		return repositorio.findByUsuarioId(userId);
	}

	/**
	 * Uso el iterador para poder encontrar rápidamente un pedido concreto del
	 * usuario logueado.
	 * 
	 * @param id
	 * @return pedido buscado. null si no lo encuentra
	 */
	public Pedido encuentraPedidoDeUsuario(long pedidoId) {
		return repositorio.getById(pedidoId);
	}

	public void borraPedidoDeUsuario(long id) {
		repositorio.deleteById(id);
	}

	/**
	 * Método para crear un pedido para el usuario logueado. Añade un pedido a la
	 * lista de pedidos del usuario. De esta manera, se almacena ordenado por fecha.
	 */
	public void creaPedido(Pedido pedido) {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		usuario.getPedidos().add(0, pedido);
	}


}
