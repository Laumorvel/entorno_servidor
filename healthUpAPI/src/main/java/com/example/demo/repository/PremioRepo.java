package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Premio;

public interface PremioRepo extends JpaRepository<Premio, Long>{
	
	@Query(value = "SELECT id FROM Premio p WHERE user_id = ?1 AND id=premio_id")
	List<Long>getUserPremio(Long user_id, Long premio_id);
	

}
