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
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class UsuarioController {

	/**
	 * Contiene la lista de usuarios que se generen
	 */
	@Autowired
	private UsuarioService servicio;

	/**
	 * Devuelve la lista de usuario.
	 * @return usuario de la base de datos (lista)
	 */
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> result = servicio.findAll();

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	/**
	 * Devuelve un usuario concreto buscado por su id.
	 * @param id
	 * @return usuario o excepción
	 */
	@GetMapping("/usuario/{id}")
	public Usuario getById(@PathVariable long id) {
		Usuario result = servicio.findById(id);
		if (result == null) {
			throw new UsuarioNotFoundException(id);
		} else {
			return result;
		}
	}
	
	/**
	 * Introduce un usuario en la base de datos.
	 * @param u
	 * @return usuario creado
	 */
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> add(@RequestBody Usuario u) {

		Usuario saved = servicio.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	/**
	 * Edita al usuario buscado por su id.
	 * @param u
	 * @param id
	 * @return usuario editado.
	 */
	@PutMapping("/usuario/{id}")
	public Usuario edit(@RequestBody Usuario u, @PathVariable long id) {
		Usuario result = servicio.edit(u, id);

		if (result == null) {
			throw new UsuarioNotFoundException(id);
		} else {
			return result;
		}
	}

	/**
	 * Elimina a un usuario de la bbdd
	 * @param id
	 * @return usuario eliminado o excepción por no encontrarlo
	 */
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Usuario result = servicio.delete(id);

		if (result == null) {
			throw new UsuarioNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Modifica la salida de la excepción creada para el usuario en caso de encontrarlo.
	 * @param ex
	 * @return excepción NOT_FOUND
	 */
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(UsuarioNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Modifica la salida de la consulta mal realizada.
	 * @param ex
	 * @return excepción BAD_REQUEST.
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
