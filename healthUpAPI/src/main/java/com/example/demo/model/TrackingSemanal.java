package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TrackingSemanal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fechaInicio;

	private LocalDate fechaFIn;

	@OneToMany
	private List<LogroFood> logrosFood = new ArrayList<>();

	@OneToMany
	private List<LogroSport> logrosSport = new ArrayList<>();

	@ManyToOne
	private User user;

	private Boolean cumplidoFood = false;

	private Boolean cumplidoSport = false;

	private Integer avanceFood = 0;

	private Integer avanceSport = 0;

	private Integer objetivoFood = user.getObjetivoFoodSemanal();// no cambia. podría quitarlos

	private Integer objetivoSport = user.getObjetivoSportSemanal();// no cambia

	/***************************************************
	 * MÉTODOS PROPIOS *
	 ***************************************************/

	/**
	 * Comprueba el valor de los avances y cambia a true las variables que marcan si
	 * se han cumplido los objetivos si así fuera.
	 */
	public void checkObjetivoSemanal() {
		this.cumplidoFood = (this.user.getObjetivoFoodSemanal() <= avanceFood);
		this.cumplidoSport = (this.user.getObjetivoSportSemanal() <= avanceSport);
	}

	/**
	 * Cuando añada un logro de comida, se añadirá 1 al avance de la comida si se ha
	 * logrado.
	 * 
	 * @param lf
	 */
	public void addLogroFood(LogroFood lf) {

		if (lf.getLogrado()) {
			avanceFood++;
		}
		this.logrosFood.add(lf);

	}

	/**
	 * Cuando añada un logro de deporte, se añadirá 1 al avance del deporte si se ha
	 * logrado.
	 * 
	 * @param ls
	 */
	public void addLogroSport(LogroSport ls) {
		if (ls.getLogrado()) {
			avanceSport++;
		}
		this.logrosSport.add(ls);
	}
	
	public void cambiaLogroSport(LogroSport ls) {
		LogroSport lsBuscado = this.logrosSport.get(this.logrosSport.indexOf(ls));
		lsBuscado.setLogrado();//métdo configurado para cambiar de true a false y viceversa.
		if(lsBuscado.getLogrado()) {
			avanceSport++;
		}else {
			avanceSport--;
		}
	}

}
