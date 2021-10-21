package beans;

/**
* Se ha creado este enumerado para aplicar el descuento que contiene dentro del
* get de la clase Usuario.
* 
* @author laura
*
*/
public enum EnumTipo {

	PENSIONISTA(5), EN_PARO(5), FAMILIA_NUMEROSA(10), FAMILIA_ESPECIAL(15), NINGUNA(0);

	private int descuento;

	EnumTipo(int descuento) {

	}

	public int getDescuento() {
		return descuento;
	}

	/**
	 * El set no sería necesario puesto que los descuentos a aplicar son fijos. Aún
	 * no he dejado por si pudiera hacerme falta al trabajar con beans
	 * 
	 * @param descuento
	 */
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

}