package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class PedidosController {

	@Autowired
	private PedidoService servicio;

	@Autowired
	private UsuarioService serviceUsuario;

	
	/**
	 * Busca todos los pedidos de la bbdd
	 * @return lista de pedidos
	 */
	@GetMapping("/pedido")
	public ResponseEntity<List<Pedido>> findAll() {
		// comprobar el usuario
		List<Pedido> result = servicio.findAll();

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	/**
	 * Busca un pedido por su id.
	 * @param id
	 * @return pedido o excepción
	 */
	@GetMapping("/pedido/{id}")
	public Pedido getById(@PathVariable long id) {
		Pedido result = servicio.findById(id);

		if (result == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return result;
		}
	}

	/**
	 * Se crea un pedido comprobando que el usuario exista (es necesario traerlo de
	 * la bbdd o no tendremos realmente el objeto usuario guardado y nos dará un
	 * error 404). Una vez encontrado el usuario, antes de crear y guardar el
	 * pedido, le establezco la dirección y el usuario del mismo.
	 * 
	 * @param u
	 * @return exception / pedido añadido con un usuario
	 */
	@PostMapping("/pedido")
	public ResponseEntity<Pedido> add(@RequestBody Usuario u) {
		Usuario user = serviceUsuario.findById(u.getId());
		if (user.getNombreUser() == null) {
			throw new UsuarioNotFoundException(u.getId());
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addPedidoUser(user));
		}

	}

	/**
	 * Método para modificar el pedido --> dirección y usuario
	 * @param p
	 * @param id
	 * @return pedido modificado
	 */
	@PutMapping("/pedido/info/{id}")
	public Pedido edit(@RequestBody Pedido p, @PathVariable long id) {
		
		Pedido result = servicio.editPedido(p, id);

		if (result == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return result;
		}
	}
	
	/**
	 * Método para incluir las líneas del pedido
	 * @param idLinea
	 * @param id
	 * @return
	 */
	@PutMapping("/pedido/{id}")
	public Pedido addLinea( @RequestBody LineaPedido l, @PathVariable long id) {
		Pedido result = servicio.edit(l.getIdLinea(), id);

		if (result == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return result;
		}
	}

	/**
	 * Borra un pedido buscdo por su id
	 * @param id
	 * @return pedido borrado
	 */
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Pedido result = servicio.delete(id);

		if (result == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Modifica la salida de la excepción del pedido
	 * @param ex
	 * @return excepción
	 */
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(PedidoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Modifica la salida de la excepción BAD_REQUEST
	 * @param ex
	 * @return excepción
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}


}
