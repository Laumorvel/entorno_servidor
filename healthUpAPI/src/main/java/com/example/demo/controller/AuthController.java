package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		String token = jwtUtil.generateToken(user.getUsername());
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
			if (userRepo.getUserName(body.getUsername()) != null) {
				throw new RuntimeException("Invalid password");
			}else {
				throw new RuntimeException("Invalid credentials");
			}
		}
	}

	/**
	 * Endpoint para comprobar que el usuario está logueado. Lo único que queremos
	 * es que nos devuelva el token, en caso contrario, indicará que el usuario no
	 * está logueado y solo quiere acceder directamente desde la url sin loguearse.
	 * 
	 * @return token o error
	 * @throws Exception
	 */
	@GetMapping("/login")
	public ResponseEntity<String> comprobarLogueo() throws Exception {
		
		try {
			return ResponseEntity.ok("");
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Endpoint para conseguir token. Cuando hace validaciones al hacer el registro,
	 * se debe validar que el email y el username no estén registrados en la bbdd.
	 * Por tanto, se necesitará el token para hacer el getMapping. Aquí hago
	 * postMapping para no tener que introducir un token, sino obtenerlo y guardarlo
	 * en el localStorage.
	 * 
	 * @return (token)
	 */
	@PostMapping("/auth/token")
	public Map<String, Object>  consigueToken(@RequestBody LoginCredentials body) {
		String token = jwtUtil.generateToken(body.getEmail());
		return Collections.singletonMap("access_token", token);
	}

}
