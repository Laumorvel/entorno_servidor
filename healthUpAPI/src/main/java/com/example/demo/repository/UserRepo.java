package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	//Método para obtener un usuario por su username
		public User findByUsername(String username);
		public User findByEmail(String email);
		
		@Query(value="SELECT username FROM user WHERE username = ?1", nativeQuery = true) 
		String getUserName(String newUsername);
		
		@Query(value= "SELECT password FROM user", nativeQuery = true)
		List<String> getPasswords();
		
		@Query(value = "SELECT email FROM user", nativeQuery = true)
		List<String> getEmails();
		
		@Query(value = "SELECT username FROM user", nativeQuery = true)
		List<String> getUsernames();
		
		
		
		
}
