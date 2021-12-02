package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HolamundoApplication {

	public static void main(String[] args) {//aquí se ejecutarán los beans que tenemos en la clase AppConfig
		ApplicationContext ctx = SpringApplication.run(HolamundoApplication.class, args);
		
		((DependantService)ctx.getBean("dependantService")).doSmth();
	}

}
