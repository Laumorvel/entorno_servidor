package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Logro;
import com.example.demo.model.User;

public interface LogroRepo extends JpaRepository<Logro, Long>{
	
	@Query(value= "SELECT * FROM logro WHERE tipo = ?1 AND fecha = ?2 AND user_id = ?3", nativeQuery = true)
	Logro getLogro(String tipo, LocalDate fecha, Long idUser);
	
	
	public List<Logro> findByUser(User user);

}
