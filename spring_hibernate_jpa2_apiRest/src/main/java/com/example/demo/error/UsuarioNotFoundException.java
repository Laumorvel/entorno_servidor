package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6071859116502536626L;
	

	public UsuarioNotFoundException(long id) {
		super("No se puede encontrar el usuario con la ID: "+ id);
	}

}
