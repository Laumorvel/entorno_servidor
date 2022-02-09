package com.example.demo.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Usuario;

import junit.framework.TestCase;

public class UsuarioControllerTest extends TestCase {
	private Usuario u;

	@BeforeEach
	public void inicial() {
		u = new Usuario("Laura Moreno", "30245304m", "lauramorelez@gmail.com", "678360561",
				"calle preciosa, 23, utrera, 41710", "laura", "laura");
	}

	/**
	 * Testeo el nombre de usuario
	 */
	@Test
	public void testGetContrasena() {
		u = new Usuario("laura", "laura");// nombreUser y contraseña
		assertTrue("laura".equals(u.getContrasena()));
	}

	/**
	 * Testeo la contraseña
	 */
	@Test
	public void testGetNombreUser() {
		u = new Usuario("laura", "laura");// nombreUser y contraseña
		assertTrue("laura".equals(u.getNombreUser()));
	}

	/**
	 * Testeo la dirección
	 */
	@Test
	public void testGetDireccion() {
		u = new Usuario("laura", "laura", "calle preciosa, 23, utrera, 41710");// nombreUser, dirección y contraseña
		assertTrue("calle preciosa, 23, utrera, 41710".equals(u.getDireccion()));
	}

}
