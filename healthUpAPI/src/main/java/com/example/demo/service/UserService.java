package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Logro;
import com.example.demo.model.User;
import com.example.demo.repository.LogroRepo;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LogroRepo logroRepo;
	
	public User getUserEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User getUsername (String username) {
		return userRepo.findByUsername(username);
	}
	
	/**
	 * Se hace una comprobación en el frontend para saber si hacer un put o un post
	 * Cuando se use el post (llamado por este método), no habrá un logro con la misma fecha y ese id de usuario en la tabla de logros
	 * @param logro
	 * @param idUser
	 * @return logro
	 */
	public User addLogro(Logro logro) {
		logro.getUser().seteaAvance(logro);
		this.logroRepo.save(logro);	
		return this.userRepo.save(logro.getUser());
	}
	
	
	public User modificaLogro(Logro logro) {
		Logro logroGuardado = this.logroRepo.getLogro(logro.getTipo(), logro.getFecha(), logro.getUser().getId());
		logro.setId(logroGuardado.getId());//le pongo la misma id para que lo sustituya al guardarlo.
		logroRepo.save(logro);
		logro.getUser().seteaAvance(logro);
		return this.userRepo.save(logro.getUser());
	}
	
	public User getUser(Long id) {
		return userRepo.getById(id);
	}
	
	public List<Logro> getRegistroUser(Long id) {
		User user = this.userRepo.findById(id).get();
		return this.logroRepo.findByUser(user);
	}
	
}
