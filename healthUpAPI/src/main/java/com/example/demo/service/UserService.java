package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.error.ApiError;
import com.example.demo.error.LogroNoExistenteException;
import com.example.demo.error.UsuarioNoExistenteException;
import com.example.demo.model.Logro;
import com.example.demo.model.User;
import com.example.demo.repository.LogroRepo;
import com.example.demo.repository.UserRepo;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LogroRepo logroRepo;

	public User getUserEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public User getUsername(String username) {
		return userRepo.findByUsername(username);
	}

	/**
	 * Se hace una comprobación en el frontend para saber si hacer un put o un post
	 * Cuando se use el post (llamado por este método), no habrá un logro con la
	 * misma fecha y ese id de usuario en la tabla de logros
	 * 
	 * @param logro
	 * @param idUser
	 * @return logro
	 */
	public Logro addLogro(Logro logro, Long id) {
		// Encuentro al usuario por id y se lo añado al logro pues no lo trae incluido
		try {
			User user = this.userRepo.findById(id).get();
			try {
				logro.setUser(user);
				user.seteaAvancePost(logro);
				this.userRepo.save(user);
				return this.logroRepo.save(logro);
			} catch (Exception e) {
				throw new LogroNoExistenteException();
			}
		} catch (Exception e) {
			throw new UsuarioNoExistenteException();

		}
	}

	public Logro modificaLogro(Logro logro, Long id, Long idLogro) {
		// Encuentro al usuario por id y se lo añado al logro pues no lo trae incluido
		try {
			User user = this.userRepo.findById(id).get();
			try {
				logro.setUser(user);
				logro.setId(idLogro);// le pongo la misma id para que lo sustituya al guardarlo.
				user.seteaAvance(logro);
				return this.logroRepo.save(logro);
			} catch (Exception e) {
				throw new LogroNoExistenteException();
			}
		} catch (Exception e) {
			throw new UsuarioNoExistenteException();
		}

	}

	public User getUser(Long id) {
		try {
			return userRepo.getById(id);
		} catch (Exception e) {
			throw new UsuarioNoExistenteException();
		}
	}

	public List<Logro> getRegistroUser(Long id) {
		User user;
		try {
			user = this.userRepo.findById(id).get();
		} catch (Exception e) {
			throw new UsuarioNoExistenteException();
		}
		try {
			return this.logroRepo.findByUser(user);
		} catch (Exception e) {
			throw new LogroNoExistenteException();
		}
	}
	
	public List<Logro> getRegistroFiltradoTipo(Long id, String tipo){
		//Hago esto para comprobar que el usuario introducido existe
		try {
			User user = this.userRepo.findById(id).get();
		} catch (Exception e) {
			throw new UsuarioNoExistenteException();
		}
		return this.logroRepo.getLogrosTipo(id, tipo);
	}

	public void reiniciaAvance() {
		// Se reinicia el avance de los usuarios
		this.userRepo.reiniciaAvance();
		// Se guardan en BBDD con los cambios
		for (User user : this.userRepo.findAll()) {
			this.userRepo.save(user);
		}
	}

	public void creaUnNoRegistrado(Long idUser, String tipo, String ayer) {
		User user = userRepo.getById(idUser);
		Logro logroFood = new Logro(ayer, user, tipo, true);
		this.logroRepo.save(logroFood);
	}
	
	public List<Long> getIdUsersWithoutFoodRegister(String fecha){
		return this.logroRepo.getIdUsersWithoutFoodRegister(fecha);
	}
	
	public List<Long> getIdUsersWithoutSportRegister(String fecha){
		return this.logroRepo.getIdUsersWithoutSportRegister(fecha);
	}
	
	//Exceptions-------------------------------------------------------
	
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
