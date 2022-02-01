package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2506811582208367968L;
	
	
	public ProductNotFoundException(long id) {
		super("No se puede encontrar el producto con la ID: " + id);
	}

}
