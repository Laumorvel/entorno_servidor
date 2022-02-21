package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.EmailAlreadyRegisteredException;
import com.example.demo.error.InvalidCredentialsException;
import com.example.demo.error.LogroNoExistenteException;
import com.example.demo.error.UsuarioNoExistenteException;
import com.example.demo.model.Logro;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public User getUserDetails() {
		try {
			String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userRepo.findByUsername(username);
		}catch(Exception e) {
			throw new InvalidCredentialsException();
		}
	}

	/**
	 * Consigue el registro de un usuario.
	 * 
	 * @param id
	 * @return lista de logros (registro)
	 */
	@GetMapping("/user/{id}/registro")
	public List<Logro> getRegistroUser(@PathVariable Long id, @RequestParam (required = false) String tipo) {
		if(tipo == null) {
			return this.userService.getRegistroUser(id);
		}else {
			return this.userService.getRegistroFiltradoTipo(id, tipo);
		}
	}

	/**
	 * Añade registro en tabla de logros cuando el usuario marque por primera vez el
	 * logro diario. El logro lo añado en el servidio a la bbdd pero lo que devuelvo
	 * es el usuario para tener los datos nuevos.
	 * 
	 * @param logro
	 * @return logro
	 */
	@PostMapping("user/{id}/newLogro")
	public Logro anadeLogro(@RequestBody Logro logro, @PathVariable Long id) {
		return userService.addLogro(logro, id);
	}

	/**
	 * Cambia el logro cuando el usuario vuelve a pulsar el botón el mismo día.
	 * 
	 * @param logro
	 * @return logro
	 */
	@PutMapping("/user/{id}/modificaLogro/{idLogro}")
	public Logro modificaLogroSport(@RequestBody Logro logro, @PathVariable Long id, @PathVariable Long idLogro) {
		return userService.modificaLogro(logro, id, idLogro);
	}
	
	//Exceptiones------------------------------------------------------------------------------

	/**
	 * Modifica la salida de la excepción del pedido
	 * 
	 * @param ex
	 * @return excepción
	 */
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(EmailAlreadyRegisteredException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Modifica la salida de la excepción BAD_REQUEST
	 * 
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
	
	@ExceptionHandler(LogroNoExistenteException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(LogroNoExistenteException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(UsuarioNoExistenteException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(UsuarioNoExistenteException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
}
