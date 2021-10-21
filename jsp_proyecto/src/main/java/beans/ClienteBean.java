package beans;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * En este bean voy a introducir las validaciones de los datos del usuario
 * 
 * @author laura
 *
 */
public class ClienteBean implements ICalculablePrecio {

	final int PRECIO_BASE_MES = 20;

	private String nombre;
	private String apellidos;
	private boolean mayorEdad;
	private String dni;
	private EnumTipo situacion;
	private double pago;// El pago se generará automáticamente. No se pedirá en el formulario de
						// inscripción

	public ClienteBean() {}

	public boolean isMayorEdad() {
		return mayorEdad;
	}

	public void setMayorEdad(boolean mayorEdad) {
		this.mayorEdad = mayorEdad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		String dniRegexp = "\\d{8}[A-HJ-NP-TV-Z]";
		Pattern.matches(dniRegexp, dni);
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		String letrasEspacios = "[A-Za-z ]";// añadiendo el espacion final también acepto espacios en el nombre para
											// validar nombres compuestos
		Pattern.matches(letrasEspacios, nombre);
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		String letrasEspacios = "[A-Za-z ]";
		Pattern.matches(letrasEspacios, nombre);
		this.apellidos = apellidos;
	}

	public EnumTipo getSituacion() {
		return situacion;
	}

	/**
	 * Para conseguir la situación de cada cliente, obtenemos el String de su
	 * situación para poder pasarlo a tipo EnumTipo
	 * 
	 * @param situacionString
	 */
	public void setSituacion(EnumTipo situacionString) {
		/*try {
			this.situacion = EnumTipo.valueOf(situacionString.toUpperCase());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
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

	@Override
	public double calculaPrecio() {
		double pago = this.situacion.getDescuento() + PRECIO_BASE_MES;
		return pago;
	}

}
