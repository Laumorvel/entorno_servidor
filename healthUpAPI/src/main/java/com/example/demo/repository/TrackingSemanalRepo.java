package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.TrackingSemanal;

public interface TrackingSemanalRepo extends JpaRepository<TrackingSemanal, Long> {

	@Query(value = "SELECT * FROM tracking_semanal WHERE user_id = ?1", nativeQuery = true)
	TrackingSemanal getTrackingDeUser(Long idUser);
}
