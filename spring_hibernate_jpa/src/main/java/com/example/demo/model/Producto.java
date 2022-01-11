package com.example.demo.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//public static int generador_codigo = 0;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precioCantidad")
	private double precioCantidad;
	
	@Column(name = "imagen")
	private String img;

	public Producto() {
		//this.id = id;
	}

	/**
	 * Cuando se creen productos estáticos para generar el catálogo, el número de
	 * productos se inicializará a 0.
	 * 
	 * @param nombre
	 * @param precio
	 */
	public Producto(String nombre, double precio, String img) {
		//this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = 0;
		this.img = img;
	}

	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	
	public double getPrecioCantidad() {
		return precioCantidad;
	}

	public void setPrecioCantidad(double precioCantidad) {
		this.precioCantidad = precioCantidad;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
