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
import com.example.demo.error.LineaPedidoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.LineaPedido;
import com.example.demo.service.LineaPedidoService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class LineaPedidoController {

	@Autowired
	private LineaPedidoService servicio;

	@GetMapping("/lineapedido")
	public ResponseEntity<List<LineaPedido>> findAll() {
		List<LineaPedido> result = servicio.findAll();
		
		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/lineapedido/{id}")
	public LineaPedido getById(@PathVariable long id) {
		LineaPedido result = servicio.findById(id);
		
		if (result == null) {
			throw new LineaPedidoNotFoundException(id);
		} else {
			return result;
		}
	}

	@PostMapping("/lineapedido")
	public ResponseEntity<LineaPedido> add(@RequestBody LineaPedido p) {
		LineaPedido saved = servicio.add(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/lineapedido/{id}")
	public LineaPedido edit(@RequestBody LineaPedido p, @PathVariable long id) {
		LineaPedido result = servicio.edit(p, id);
		
		if (result == null) {
			throw new LineaPedidoNotFoundException(id);
		} else {
			return result;
		}
	}

	@DeleteMapping("/lineapedido/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		LineaPedido result = servicio.delete(id);
		
		if (result == null) {
			throw new UsuarioNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ExceptionHandler(LineaPedidoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(LineaPedidoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

}
