package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio;

	@Column(name = "imagen")
	private String img;

	//Esta anotación hará que la bbdd ignore esta propiedad
	//Solo la necesito para poder hacer una petición incluyendo la cantidad en el RequestBody
	//Luego, aunque nos aparezca en el resultado de la petición, no aparece en la base de datos.
	@Transient//¡Cuidado! --> debe importarse de persistence (no de bean)
	private int cantidad;

	public Producto() {
	}

	/**
	 * Cuando se creen productos estáticos para generar el catálogo, el número de
	 * productos se inicializará a 0.
	 * 
	 * @param nombre
	 * @param precio
	 */
	public Producto(String nombre, double precio, String img) {
		this.nombre = nombre;
		this.precio = precio;
		this.img = img;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}

}
