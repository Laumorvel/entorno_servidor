package com.example.demo.controller;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.EmailAlreadyRegisteredException;
import com.example.demo.model.LogroFood;
import com.example.demo.model.LogroSport;
import com.example.demo.model.TrackingSemanal;
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
	public ResponseEntity<Long> getUserDetails() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(userRepo.findByUsername(username).getId());
	}

	/**
	 * Comprueba que el email indicado no se encuentra en la bbdd. Así, el usuario
	 * puede usarlo para registrarse.
	 * 
	 * @param email
	 * @return usuario o null
	 */
	@GetMapping("/users/{email}")
	public User checkEmailUsers(@PathVariable String email) {
		return userService.getUserEmail(email);
	}

	@GetMapping("usernames/{username}")
	public User checkUsernameUsers(@PathVariable String username) {
		return userService.getUsername(username);
	}

	/**
	 * Añade cambios cuando el usuario pulsa el botón de logro.
	 * @param logroSport
	 * @param id
	 * @throws Exception
	 */
	@PostMapping("user/{id}/trackingSemanalSport")
	public TrackingSemanal anadeLogro(@RequestBody LogroSport logroSport, @PathVariable Long id){
		return userService.addTrackingSemanalLS(logroSport, id);
	}
	
	@PostMapping("user/{id}/trackingSemanalFood")
	public TrackingSemanal anadeLogro(@RequestBody LogroFood logroFood, @PathVariable Long id) {
		return userService.addTrackingSemanalLF(logroFood, id);
	}
	
	@PutMapping("user/{id}/trackingSemanalSport")
	public TrackingSemanal modificaLogro(@RequestBody LogroSport logroSport, @PathVariable Long id) {
		return userService.cambiaTrackingSemanalLS(logroSport, id);
	}

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
}
