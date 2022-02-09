package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginCredentials;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JWTUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/auth/register")
	public Map<String, Object> registerHandler(@RequestBody User user) {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		user = userRepo.save(user);
		String token = jwtUtil.generateToken(user.getEmail());
		return Collections.singletonMap("access_token", token);
	}

	@PostMapping("/auth/login")
	public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUsername(), body.getPassword());

			authManager.authenticate(authInputToken);

			String token = jwtUtil.generateToken(body.getUsername());

			return Collections.singletonMap("access_token", token);
		} catch (AuthenticationException authExc) {
			List<User> users = userRepo.findAll();
			for (User user : users) {
				if (user.getUsername().equals(body.getUsername())) {
					throw new RuntimeException("Invalid password");
				} else if (user.getPassword().equals(body.getPassword())) {
					throw new RuntimeException("Invalid username");
				}
			}
			throw new RuntimeException("Invalid username and password");

		}
	}
	
	@PostMapping("auth/loginGetIdUser")
	public Map<String, Object> loginGetIdUser(@ResquestBody String username){
		Long idUser = userRepo.findByUsername(username).getId();
		
		
	}

}
