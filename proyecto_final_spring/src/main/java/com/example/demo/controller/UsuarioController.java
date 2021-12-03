package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String login(String login, Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	/**
	 * Comprobar si el usuario está o no registrado gracias a un método del servicio
	 * para que este controlador no sea demasiado grande. Además se han incluído
	 * aquí las comprobaciones de las validaciones que hemos establecido con
	 * mensajes de error. Se ha utilizado la anotación requestparam para poder
	 * traernos los valores de los inputs del formulario.
	 * 
	 * @return devuelve al login si el usuario no es correo y al memú del usuario
	 *         cuando los datos sean correctos
	 */
	@PostMapping("/login/submit")
	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	public String nuevoUser(Model model, @Valid @ModelAttribute("usuario") Usuario usuario,
			BindingResult bindingResult) {
		Usuario usuario1 = servicio.findByNameAndPassword(usuario);
		if (usuario != null && !bindingResult.hasErrors()) {
			model.addAttribute("usuario", usuario1);
			sesion.setAttribute("usuario", usuario1);
			return "menuPersonal";
		} else {
			return "login";
		}
	}
}
