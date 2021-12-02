package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {//La clase debe llamarse AppCOnfig
	

	@Bean
	public Holamundo holamundo() {//podemos tener todos los bean que queramos con un método que ejecute el constructor
		return new Holamundo();
	}
	
	@Bean
	public DependantService dependantService() {
		DependantService ds = new DependantService();
		ds.setService1(service1());
		ds.setService2(service2());
		
		return ds;
	}
	
	@Bean
	public Service1 service1() {
		return new Service1();
	}
	
	@Bean
	public Service2 service2() {
		return new Service2();
	}
	
}
