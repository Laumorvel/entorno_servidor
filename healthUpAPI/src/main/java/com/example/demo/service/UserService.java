package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LogroFood;
import com.example.demo.model.LogroSport;
import com.example.demo.model.TrackingSemanal;
import com.example.demo.model.User;
import com.example.demo.repository.TrackingSemanalRepo;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TrackingSemanalRepo trackingRepo;
	
	public User getUserEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User getUsername (String username) {
		return userRepo.findByUsername(username);
	}
	
	/**
	 * Este método solo se llamará cuando se quiera añadir un logro sport.
	 * Se hace una comprobación en el frontend para saber si hacer un put o un post
	 * Cuando se use el post (llamado por este método), no habrá un logro sport con la misma fecha en la lista
	 * @param ls
	 * @param idUser
	 * @return tracking del usuario cambiado
	 */
	public TrackingSemanal addTrackingSemanalLS(LogroSport ls, Long idUser) {
		//Consigo el tracking del usuario
		TrackingSemanal tracking = trackingRepo.getTrackingDeUser(idUser);
		//Le añado el logro y setea el avance si su boolena es true
		tracking.addLogroSport(ls);
		//Seteo el boolean del objetivo si se diera el caso
		tracking.checkObjetivoSemanal();
		//salvo el tracking
		return trackingRepo.save(tracking);
	}
	
	/**
	 * Idéntico al método anterior pero con el LogroFood
	 * @param lf
	 * @param idUser
	 * @return tracking del usuario cambiado
	 */
	public TrackingSemanal addTrackingSemanalLF(LogroFood lf, Long idUser) {
		//Consigo el tracking del usuario
		TrackingSemanal tracking = trackingRepo.getTrackingDeUser(idUser);
		//Le añado el logro y setea el avance si su boolena es true
		tracking.addLogroFood(lf);
		//Seteo el boolean del objetivo si se diera el caso
		tracking.checkObjetivoSemanal();
		//salvo el tracking
		return trackingRepo.save(tracking);
	}
	
	public TrackingSemanal cambiaTrackingSemanalLS(LogroSport ls, Long idUser) {
		//Consigo el tracking del usuario
		TrackingSemanal tracking = trackingRepo.getTrackingDeUser(idUser);
		//Modifico el logro
		tracking.cambiaLogroSport(ls);
		//Seteo el boolean del objetivo si se diera el caso
		tracking.checkObjetivoSemanal();
		//guardo el tracking
		return trackingRepo.save(tracking);
	}

}
