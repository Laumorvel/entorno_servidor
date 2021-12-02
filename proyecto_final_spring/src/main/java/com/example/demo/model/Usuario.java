package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

	public static int GENERADOR_COD = 2;
	private long id;
	private String nombre;
	private String dni;
	private String email;
	private String telefono;
	private String direccion;
	private List<Pedido> pedidos;
	private String nombreUser;
	private String contrasena;

	/**
	 * El id se autoaumenta para que se autogenere sólo. No se añade al constructor.
	 * No se hará formulario para registrar los formularios, sino que tendremos
	 * datos estáticos dentro de la aplicación para tener ya dos usuaerios
	 * registrados.
	 * 
	 * @param id
	 * @param nombre
	 * @param dni
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param nombreUser
	 * @param contrasena
	 */
	public Usuario(String nombre, String dni, String email, String telefono, String direccion, String nombreUser,
			String contrasena) {
		this.id = GENERADOR_COD++;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pedidos = new ArrayList<Pedido>();
		this.nombreUser = nombreUser;
		this.contrasena = contrasena;
	}

	/**
	 * Constructor vacío para pasárselo dentro del getMapping del login
	 */
	public Usuario() {
		this.id = GENERADOR_COD++;
	}
	
	public Usuario(String nombreUser, String contrasena) {
		this.id = GENERADOR_COD++;
		this.nombreUser = nombreUser;
		this.contrasena = contrasena;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni) && id == other.id;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", email=" + email + ", telefono="
				+ telefono + ", direccion=" + direccion + ", pedidos=" + pedidos + ", nombreUser=" + nombreUser
				+ ", contrasena=" + contrasena + "]";
	}

}