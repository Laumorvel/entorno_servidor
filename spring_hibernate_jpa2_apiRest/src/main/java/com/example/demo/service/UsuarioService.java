package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private List<Usuario> usuarios = new ArrayList<>();

	@Autowired
	private UsuarioRepository repositorio;

	/**
	 * Establecemos los siguiente atributos para comprobar los inicios de sesión y
	 * poder acceder siempre al id del usuario y, así, recuperarlo.
	 */
	private boolean logueado = false;
	private long userId = 0;

	/**
	 * Elimina un usuario de la bbdd.
	 * @param id
	 * @return usuario eliminado.
	 */
	public Usuario delete(long id) {
		if (repositorio.existsById(id)) {
			Usuario p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}

	/**
	 * Método para añadir un usuario a nuestra lista de usuarios.
	 * 
	 * @param e
	 * @return usuario añadido
	 */
	public Usuario add(Usuario e) {
		usuarios.add(e);
		return repositorio.save(e);
	}

	/**
	 * Edita un usuario de la bbdd.
	 * @param p
	 * @param id
	 * @return usuario editado.
	 */
	public Usuario edit(Usuario p, long id) {
		if (repositorio.existsById(id)) {
			p.setId(id);
			return repositorio.save(p);
		} else {
			return null;
		}
	}

	/**
	 * Busca a todos los usuarios de la bbdd.
	 * @return lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isLogueado() {
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}

	/**
	 * Método para que nos devuelva todos nuestros usuarios.
	 * 
	 * @return lista de usuarios creados hasta el momento
	 */
	public List<Usuario> findAll() {
		return repositorio.findAll();
	}

	/**
	 * Método para encontrar al usuario desde el controlador del usuario por su id
	 * una vez que se cree, es decir, en el postConstruct cuando le haya dado al
	 * submit. Devuelve el usuario para confirmar que el usuario está registrado
	 * puesto que hacemos las comprobaciones en este mismo método.
	 * 
	 * @param id
	 * @return usuario introducido si lo encuentra o null en caso contrario
	 */
	public Usuario findByNameAndPassword(Usuario usuario) {
		boolean encontrado = false;
		Usuario usuario1 = null;
		int i = 0;
		while (!encontrado && i < repositorio.findAll().size()) {
			if ((repositorio.findAll().get(i).getNombreUser().equals(usuario.getNombreUser()))
					&& (repositorio.findAll().get(i).getContrasena().equals(usuario.getContrasena()))) {
				encontrado = true;
				usuario1 = repositorio.findAll().get(i);
			} else {
				i++;
			}
		}

		return usuario1;
	}

	/**
	 * Ya no utilizo los usuarios que voy creando en el servicio, sino los que tengo
	 * en el repositorio de usuarios. Si no coloco el orElse() daría un error de BAD
	 * REQUEST puesto que es una operación lazy loading. Por tanto, solo te devuelve
	 * una referencia y no accede realmente a la bbdd y, por eso, no encuentra el
	 * serializable del objeto, porque solo accede a sus propiedades, no a l objeto
	 * en sí.
	 * 
	 * @param id
	 * @return el usuario encontrado. Si no lo ha encontrado, devuleve null.
	 */
	public Usuario findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

}
