package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService servicio;
	
	@GetMapping("/producto")
	public List<Producto> findAll(){
		return servicio.findAll();
	}

	
	
}
