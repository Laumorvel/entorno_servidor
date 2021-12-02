package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.EmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired // lo inyectamos
	private EmpleadoService servicio;

	@GetMapping("empleado/list")
	public String listAll(Model model) {
		model.addAttribute("listaEmpleados", servicio.findAll());
		return "list";

	}
}
