package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;

@Service
public class EmpleadoService {
	private List<Empleado> repositorio = new ArrayList<>();

	public Empleado add(Empleado e) {
		repositorio.add(e);
		return e;
	}

	public List<Empleado> findAll() {// Por convención se suele llamar findAll()
		return repositorio;
	}

	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Empleado(1, "José Pérez", "joselito@domionio", "345123123"),
						new Empleado(2, "María Pérez", "mery@domionio", "456456456")));
	}
}
