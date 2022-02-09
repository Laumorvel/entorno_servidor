package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	//Método para obtener un usuario por su email
		public User findByUsername(String username);
}
