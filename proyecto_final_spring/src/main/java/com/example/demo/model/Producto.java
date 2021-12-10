package com.example.demo.model;

import java.util.Objects;

public class Producto {

	private long id;
	public static int generador_codigo = 0;
	private String nombre;
	private double precio;
	private int cantidad;

	public Producto() {
		this.id = generador_codigo++;
	}

	/**
	 * Cuando se creen productos estáticos para generar el catálogo, el número de
	 * productos se inicializará a 0.
	 * @param nombre
	 * @param precio
	 */
	public Producto(String nombre, double precio) {
		this.id = generador_codigo++;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = 0;
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
