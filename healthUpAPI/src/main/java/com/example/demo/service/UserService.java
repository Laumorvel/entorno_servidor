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
	public Logro addLogro(Logro logro, Long id) {
		//Encuentro al usuario por id y se lo añado al logro pues no lo trae incluido
		User user = this.userRepo.findById(id).get();
		logro.setUser(user);
		logro.getUser().seteaAvance(logro);
		 this.userRepo.save(logro.getUser());
		return this.logroRepo.save(logro);	
	}
	
	
	public Logro modificaLogro(Logro logro, Long id, Long idLogro) {
		//Encuentro al usuario por id y se lo añado al logro pues no lo trae incluido
		User user = this.userRepo.findById(id).get();
		logro.setUser(user);
		logro.setId(idLogro);//le pongo la misma id para que lo sustituya al guardarlo.
		user.seteaAvance(logro);
		return this.logroRepo.save(logro);
	}
	
	public User getUser(Long id) {
		return userRepo.getById(id);
	}
	
	public List<Logro> getRegistroUser(Long id) {
		User user = this.userRepo.findById(id).get();
		return this.logroRepo.findByUser(user);
	}
	
}
