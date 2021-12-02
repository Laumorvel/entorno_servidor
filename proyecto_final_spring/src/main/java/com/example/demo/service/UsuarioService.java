package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {

	private List<Usuario> usuarios = new ArrayList<>();

	/**
	 * Método para añadir un usuario a nuestra lista de usuarios.
	 * @param e
	 * @return
	 */
	public Usuario add(Usuario e) {
		usuarios.add(e);
		return e;
	}

	/**
	 * Método para que nos devuelva todos nuestros usuarios.
	 * @return lista de usuarios creados hasta el momento
	 */
	public List<Usuario> findAll() {
		return usuarios;
	}

	/**
	 * Le añadimos a la lista de usuarios un par de usuario de prueba para poder
	 * probar la aplicación.
	 */
	@PostConstruct
	public void init() {
		usuarios.addAll(Arrays.asList(new Usuario("pepi", "pepi"), (new Usuario("loli", "loli"))));
	}

	/**
	 * Método para encontrar al usuario desde el controlador del usuario por su id
	 * una vez que se cree, es decir, en el postConstruct cuando le haya dado al
	 * submit. Devuelve al usuario si lo encuentra y devolverá null en caso
	 * contrario.
	 * 
	 * @param id
	 * @return
	 */
	public Usuario findById(long id) {
		Usuario result = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < usuarios.size()) {
			if (usuarios.get(i).getId() == id) {
				encontrado = true;
				result = usuarios.get(i);
			} else {
				i++;
			}
		}

		return result;
	}

}
