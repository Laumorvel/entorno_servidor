package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	@Autowired
	private LineaPedidoRepository lineaRepo;

	@Autowired
	private UsuarioRepository userRepo;

	public void add(Pedido e) {
		repositorio.save(e);
	}

	public List<Pedido> findAll() {
		return repositorio.findAll();
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

	/**
	 * utilizo esta propiedad para que pueda reconocer el pedido nuevo que se ha
	 * reliado y poder obtenerlo más fácilmente.
	 */
	private long pedidoId = 0;

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

	public Pedido edit(long idLinea, long id) {
		if (repositorio.findById(id) != null) {
			Pedido p = repositorio.findById(id).orElse(null);
			p.getLineasPedido().add(lineaRepo.findById(idLinea).orElse(null));

			return repositorio.save(p);
		} else {
			return null;
		}
	}

	/**
	 * Método para modificar la información de envío de un pedido e incluir a otro
	 * usuario. Se comunica si el usuario introducido en el pedido no existe o si el
	 * pedido marcado no existe.
	 */
	public Pedido editPedido(Pedido p, long idPedido) {
System.out.println(p);
		if (repositorio.findById(idPedido).orElse(null) != null) {
			if (userRepo.findById(p.getUsuario().getId()).orElse(null) != null) {
				Usuario u = userRepo.getById(p.getUsuario().getId());
				Pedido pedidoOriginal = repositorio.findById(idPedido).orElse(null);
				pedidoOriginal.setDireccion(p.getDireccion());
				pedidoOriginal.setUsuario(u);
				return repositorio.save(pedidoOriginal);
			} else {
				throw new UsuarioNotFoundException(p.getUsuario().getId());
			}
		}else {
			throw new PedidoNotFoundException(idPedido);
		}

	}

	public Pedido findById(long pedido) {
		return repositorio.findById(pedido).orElse(null);
	}

	public Pedido addPedido(Pedido p) {
		return repositorio.save(p);
	}

	public Pedido borraPedidoDeUsuario(long id) {
		if (repositorio.existsById(id)) {
			Pedido p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}

	public Pedido delete(long id) {
		if (repositorio.existsById(id)) {
			Pedido p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}

	/**
	 * Método para crear un pedido para el usuario logueado. Añade un pedido a la
	 * lista de pedidos del usuario. De esta manera, se almacena ordenado por fecha.
	 */
	public Pedido creaPedido(Pedido pedido) {
		return repositorio.save(pedido);
	}

	public Pedido addPedidoUser(Usuario u) {
		Pedido p = new Pedido(u);
		p.setDireccion(u.getDireccion());
		p.setUsuario(u);
		return repositorio.save(p);
	}

}
