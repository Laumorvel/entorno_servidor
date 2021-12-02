package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/") //@ResponseBody --> en el return ponemos directamente una cadena que será lo que envíe directamente al navegador
	public String welcome(Model model) {
		model.addAttribute("mensaje", "Holaaaaa");
		return "index";//por defecto siempre devuelven un string
	}
	
	@GetMapping("/greeting")//Búsqueda: http://localhost:8080/greeting?name=cosas
	public String greeting(@RequestParam( name="name", required=false, defaultValue="World") String name, Model model){
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/greeting/{name}")
	public String greetingPath(@PathVariable(name="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/portada")
	public String portada(Model model) {
		model.addAttribute("portada", "¡Bienvenido a nuestra página!");
		return "portada";
	}
	
	@GetMapping("/aboutUs/{nombre}")
	public String aboutUs(Model model, @PathVariable(name="nombre", required=false) String nombre) {
		model.addAttribute("nombre", nombre);
		String info = "Somos una empresa familiar y hacemos los mejores dulces de toda Andalucía";
		model.addAttribute("info", info);
		return "aboutUs";
	}
	
	@GetMapping("/contacto/{nombre}")
	public String contact(Model model, @PathVariable(name="nombre", required=false) String nombre) {
		model.addAttribute("nombre", nombre);
		String info = "Somos una empresa familiar y hacemos los mejores dulces de toda Andalucía";
		model.addAttribute("info", info);
		return "contact";
	}

}
