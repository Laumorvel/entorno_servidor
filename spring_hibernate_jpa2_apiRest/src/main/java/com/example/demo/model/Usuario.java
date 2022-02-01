package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "dni", nullable = false)
	private String dni;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "userName", nullable = false)
	private String nombreUser;

	@Column(name = "contrasena", nullable = false)
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
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.nombreUser = nombreUser;
		this.contrasena = contrasena;
	}

	/**
	 * Constructor vacío para pasárselo dentro del getMapping del login.
	 */
	public Usuario() {
	}

	/**
	 * Constructor para introducir varios datos del usuario
	 * 
	 * @param nombreUser
	 * @param contrasena
	 * @param direccion
	 */
	public Usuario(String nombreUser, String contrasena, String direccion) {
		this.nombreUser = nombreUser;
		this.direccion = direccion;
		this.contrasena = contrasena;
	}

	/**
	 * Otro constructor con el que poder incluir también el teléfono y el email
	 * desde la creación.
	 * 
	 * @param nombreUser
	 * @param contrasena
	 * @param direccion
	 * @param telefono
	 * @param email
	 */
	public Usuario(String nombreUser, String contrasena, String direccion, String telefono, String email) {
		this.nombreUser = nombreUser;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.email = email;
	}

	public Usuario(String nombreUser, String contrasena) {
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
				+ telefono + ", direccion=" + direccion + ", nombreUser=" + nombreUser + ", contrasena=" + contrasena
				+ "]";
	}

}
