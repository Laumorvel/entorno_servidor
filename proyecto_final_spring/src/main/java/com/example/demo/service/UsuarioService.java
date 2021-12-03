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
	 * 
	 * @param e
	 * @return usuario añadido
	 */
	public Usuario add(Usuario e) {
		usuarios.add(e);
		return e;
	}

	/**
	 * Método para que nos devuelva todos nuestros usuarios.
	 * 
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
		usuarios.addAll(Arrays.asList(new Usuario("pepi", "pepi123"), (new Usuario("loli", "loli123"))));
	}

	/**
	 * Método para encontrar al usuario desde el controlador del usuario por su id
	 * una vez que se cree, es decir, en el postConstruct cuando le haya dado al
	 * submit. Devuelve el usuario para confirmar que el usuario está registrado
	 * puesto que hacemos las comprobaciones en este mismo método.
	 * @param id
	 * @return usuario introducido si lo encuentra o null en caso contrario
	 */
	public Usuario findByNameAndPassword(Usuario usuario) {
		boolean encontrado = false;
		Usuario usuario1 = null;
		int i = 0;
		while (!encontrado && i < usuarios.size()) {
			if ((usuarios.get(i).getNombreUser().equals(usuario.getNombreUser()))
					&& (usuarios.get(i).getContrasena().equals(usuario.getContrasena()))) {
				encontrado = true;
				usuario1 = usuarios.get(i);
			} else {
				i++;
			}
		}

		return usuario1;
	}

}
