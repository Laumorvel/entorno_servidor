package com.example.demo.model;


import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Logro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha = LocalDate.now();
	
	private Boolean logradoDia = false;
	
	@ManyToOne
	private User user;
	
	private String tipo;
	
	
	/**
	 * HashCode e equal por fecha y tipo
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logro other = (Logro) obj;
		return Objects.equals(fecha, other.fecha) && tipo == other.tipo;
	}

	public Logro(LocalDate fecha, Boolean logradoDia, User user, String tipo) {
		this.fecha = fecha;
		this.logradoDia = logradoDia;
		this.user = user;
		this.tipo = tipo;
	}
	
	

}