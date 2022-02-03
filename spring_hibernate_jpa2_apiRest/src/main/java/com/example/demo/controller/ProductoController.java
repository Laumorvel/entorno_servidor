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
import com.example.demo.error.ProductNotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService servicio;
	
	/**
	 * Devuelve la lista de productos.
	 * @return lista de productos o excepción
	 */
	@GetMapping("/producto")
	public ResponseEntity<List<Producto>> findAll(){
		List<Producto> result = servicio.findAll();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Devuelve un  producto concreto buscado por su id.
	 * @param id
	 * @return producto concreto o null.
	 */
	@GetMapping("/producto/{id}") 
	public Producto getById(@PathVariable long id) {
		Producto result = servicio.findById(id);
		
		if (result == null) {
			throw new ProductNotFoundException(id);
		} else {
			return result;
		}
	}
	
	/**
	 * Crea un nuevo producto.
	 * @param p
	 * @return producto creado
	 */
	@PostMapping("/producto")
	public ResponseEntity<Producto> add(@RequestBody Producto p) {
		Producto saved = servicio.add(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	/**
	 * Modifica un producto, buscado por ir.
	 * @param p
	 * @param id
	 * @return el producto modificado.
	 */
	@PutMapping("/producto/{id}")
	public Producto edit(@RequestBody Producto p, @PathVariable long id) {
		Producto result = servicio.edit(p, id);
		
		if (result == null) {
			throw new ProductNotFoundException(id);
		} else {
			return result;
		}
	}
	
	/**
	 * Borra un producto buscado por id.
	 * @param id
	 * @return producto borrado
	 */
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Producto result = servicio.delete(id);
		
		if (result == null) {
			throw new ProductNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * Se modifica la salida de la excepción.
	 * @param ex
	 * @return excepción de producto
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Se modifica la salida de la excepción de JsonMapping con BAD_REQUEST.
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
