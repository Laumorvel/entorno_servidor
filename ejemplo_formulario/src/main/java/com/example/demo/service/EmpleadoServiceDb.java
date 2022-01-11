package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

public class EmpleadoServiceDb implements EmpleadoService {

	@Autowired
	private EmpleadoRepository repositorio;

	@Override
	public Empleado add(Empleado e) {
		return repositorio.save(e);
	}

	@Override
	public List<Empleado> findAll() {
		return repositorio.findAll();
	}

	// como finById devuelve un Optional, de esta manera devolverá null
	// en caso de no encontrarlo. Si no, con optional, no devolvería
	// nada.
	@Override
	public Empleado findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Empleado edit(Empleado e) {
		return repositorio.save(e);
	}

}
