package logic;

import java.util.Objects;

/**
 * Se guardarán todos los artículos que deseamos incluir. Se incluirán a mano,
 * puesto que no se nos pide un formulario para introducir productos sino solo
 * para mostrarlos y comprarlos
 * 
 * @author laura
 *
 */
public class ProductoBean {

	// la cantidad la introducimos por el html
	private String nombre;
	private double precio;

	public ProductoBean(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoBean other = (ProductoBean) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "CatalogoBean [nombre=" + nombre + ", precio=" + precio + "]";
	}

}
