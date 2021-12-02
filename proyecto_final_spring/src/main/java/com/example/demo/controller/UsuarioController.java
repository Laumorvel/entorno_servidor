package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	/**
	 * Contiene la lista de usuarios que se generen
	 */
	@Autowired
	private UsuarioService servicio;

	/**
	 * Contiene la sesión generada por cada usuario
	 */
	@Autowired
	private HttpSession sesion;

	/**
	 * En caso de no estar registrado el usuario en la aplicación, lo cual
	 * comprobaremos gracias a la sesión, se guiará al login. En caso contrario y,
	 * además, no tener coincidir el usuario con la contraseña, nos dirigiría al
	 * login pero con un mensaje de error.
	 * 
	 * @param login
	 * @param model
	 * @return
	 */
	@GetMapping({ "/login", "/" })
	public String login(@RequestParam(name = "login", required = false) String login, Model model,
			BindingResult bindingResult) {
		model.addAttribute("user", new Usuario());
		return "login";
	}

	/**
	 * Comprobar si el usuario está o no registrado gracias a un método del servicio
	 * para que este controlador no sea demasiado grande.
	 * 
	 * @return
	 */
	@PostMapping("/login/submit")
	public String nuevoUser(Model model, @PathVariable int id) {
		Usuario usuario = servicio.findById(id);
		if(usuario != null) {
			model.addAttribute("usuario", usuario);
			return "menuPersonal";
		}else {
			return "redirect:/login";
		}
	}
}
