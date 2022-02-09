package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany
	private List<LogroFood> foodTracker = new ArrayList<>();
	
	@OneToMany
	private  List<LogroSport> sportTracker = new ArrayList<>();

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
	

}
