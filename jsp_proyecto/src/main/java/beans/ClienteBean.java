package beans;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En este bean voy a introducir las validaciones de los datos del usuario. Hace
 * uso de enumerado de la situacion del usuario para que se calcule su precio a
 * pagar por si debe aplicar algún descuento.
 * 
 * @author laura
 *
 */
public class ClienteBean implements ICalculablePrecio {
	final String letrasEspacios = "^[a-zA-Zà-üÀ-ÜñÑ .]+$";// expresión regular para aceptar todo tipo de letras y
															// espacios sin números
	final int PRECIO_BASE_MES = 20;
	final String regexDni = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";// expresión regular para
																							// aceptar dnis que sean
																							// reales

	private String nombre;
	private String apellidos;
	private boolean mayorEdad;
	private String dni;
	private EnumTipo situacion;
	private double pago;// El pago se generará automáticamente. No se pedirá en el formulario de
						// inscripción

	/**
	 * CONSTRUCTOR VACÍO Todos los sets validan las entradas de los datos del
	 * usuario
	 */
	public ClienteBean() {
	}

	// GETTERS Y SETTERS---------------------

	public boolean isMayorEdad() {
		return mayorEdad;
	}

	public void setMayorEdad(boolean mayorEdad) {
		this.mayorEdad = mayorEdad;
	}

	public String getDni() {
		return dni;
	}

	/**
	 * Validación del dni SOLO FUNCIONARÁ CUANDO EL DNI SEA AUTÉNTICO y el input no
	 * se haya dejado vacío
	 * 
	 * @param dni
	 * @throws Exception
	 */
	public void setDni(String dni) throws Exception {
		if (validarNIF(dni) && dni.length() > 0) {
			this.dni = dni;
		} else {
			throw new Exception("Debe introducir un dni correcto");
		}
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Funcionará con espacios (para nombres compuestos) y letras como ñ à ... pero
	 * no admite números
	 * 
	 * @param nombre
	 * @throws Exception
	 */
	public void setNombre(String nombre) throws Exception {
		if (validarexpReg(nombre, letrasEspacios) && (nombre.length() > 0)) {
			this.nombre = nombre;
		} else {
			throw new Exception("Se debe completar el campo nombre y no debe contener números");
		}
	}

	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Idem a setNombre
	 * 
	 * @param apellidos
	 * @throws Exception
	 */
	public void setApellidos(String apellidos) throws Exception {
		if (validarexpReg(apellidos, letrasEspacios) && (apellidos.length() > 0)) {
			this.apellidos = apellidos;
		} else {
			throw new Exception("Se debe completar el campo apellidos y no debe contener números");
		}
	}

	public EnumTipo getSituacion() {
		return situacion;
	}

	/**
	 * Para conseguir la situación de cada cliente, como en el formulario se ha
	 * introducido un select con las opciones predefinidas, no habrá errores.
	 * Siempre hay una opción marcada.
	 * @param situacionStringnif
	 */
	public void setSituacion(EnumTipo situacionString) {
		this.situacion = situacionString;
	}

	public double getPago() {
		return pago;
	}

	/**
	 * Como necesitamos el setter de pago y no podemos incluir el método
	 * calculaPrecio en el constructor (pues necesitaria del enumerado como
	 * parámetro), lo incluimos dentro del setter para que no haya problemas
	 * 
	 * @param pago
	 */
	public void setPago(double pago) {
		calculaPrecio();
	}

	/**
	 * Se hace uso del método de la interfaz ICalculablePrecio
	 */
	@Override
	public double calculaPrecio() {
		double pago = PRECIO_BASE_MES - this.situacion.getDescuento();
		return pago;
	}
	
	
	
	
	
	//MÉTODOS BÁSICOS DE LA CLASE--------------

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteBean other = (ClienteBean) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "ClienteBean [nombre=" + nombre + ", apellidos=" + apellidos + ", mayorEdad=" + mayorEdad + ", dni="
				+ dni + ", situacion=" + situacion + "]";
	}

	
	
	
	//MÉTODOS ADICIONALES -----------------
	/**
	 * Método genérico privado para validar expresiones regulares
	 * @param value
	 * @param regexp
	 * @return
	 */
	private Boolean validarexpReg(String value, String regexp) {
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * Método para validar dni auténtico
	 * @param nif
	 * @return
	 */
	private boolean validarNIF(String nif) {
		boolean correcto = false;
		Pattern pattern = Pattern.compile(regexDni);
		Matcher matcher = pattern.matcher(nif);
		if (matcher.matches()) {
			String letra = matcher.group(2);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int index = Integer.parseInt(matcher.group(1));
			index = index % 23;
			String reference = letras.substring(index, index + 1);
			if (reference.equalsIgnoreCase(letra)) {
				correcto = true;
			}
		}
		return correcto;
	}

}
