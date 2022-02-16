/**
 * 
 */
package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserRepo;

/**
 * @author laura
 * Mi versión de spring es la 5.3.13
 *
 */
@Component
public class TareasProgramadas {
	
	@Autowired
	public UserRepo userRepo;
	
	//Cada minuto
	//Comprobar si en las listas del usuario hay algún objetivo con la fecha del día anterior (porque son las 00:00), ya ha pasado el día.
	//De momento lo haré con cada minuto.
	//Debería ser cada medianoche pero para probarlo lo dejaré así
	@Scheduled(fixedRate = 60000)//@Scheduled(cron = "@midnight")//"0 0 0 * * *" - puede que tenga que ponerle zone = "xxxxxx"
	public void checkObjectivesEveryDay() {
		
		
		
		
	}

}
