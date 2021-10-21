package beans;

/**
 * Con esta interfaz, se calculará el precio que debe pagar cada cliente por las
 * clases dependiendo de su situación. Con el enumerado es posible acceder al
 * descuento a aplicar y se calculará dentro del método calculaPrecio
 * 
 * @author laura
 *
 */
public interface ICalculablePrecio {

	double calculaPrecio();

}
