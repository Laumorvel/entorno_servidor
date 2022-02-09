package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	//Método para obtener un usuario por su email
		public User findByUsername(String username);
		
		@Query(value="SELECT username FROM user", nativeQuery = true) 
		List<String> getUserNames();
		
		@Query(value= "SELECT password FROM user", nativeQuery = true)
		List<String> getPasswords();
		
		
}
