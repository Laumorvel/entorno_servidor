package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String surname;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String username;

	private String email;

	private Integer objetivoFoodSemanal;

	private Integer objetivoSportSemanal;

	private Boolean logradoSemanaFood = false;

	private Boolean logradoSemanaSport = false;

	private Integer avanceSemanaFood = 0;

	private Integer avanceSemanaSport = 0;

	public User(String name, String surname, String password, String username, String email,
			Integer objetivoFoodSemanal, Integer objetivoSportSemanal) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.username = username;
		this.email = email;
		this.objetivoFoodSemanal = objetivoFoodSemanal;
		this.objetivoSportSemanal = objetivoSportSemanal;
	}

	public User(String name, String surname, String password, String username, String email) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.username = username;
		this.email = email;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void seteaAvance(Logro logro) {
		if (logro.getTipo().equals("food")) {
			this.avanceSemanaFood = (logro.getLogradoDia()) ? this.avanceSemanaFood++ : this.avanceSemanaFood--;
		} else if (logro.getTipo().equals("sport")) {
			this.avanceSemanaSport = (logro.getLogradoDia()) ? this.avanceSemanaSport++ : this.avanceSemanaSport--;
		}
		this.logradoSemanaFood = (this.objetivoFoodSemanal <= this.avanceSemanaFood);
		this.logradoSemanaSport = (this.objetivoSportSemanal <= this.avanceSemanaSport);
	}
	

}
