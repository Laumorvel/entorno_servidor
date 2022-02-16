package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				//login
				registry.addMapping("/auth/login").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//register
				registry.addMapping("/auth/register").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//comprobación logueo con guardián
				registry.addMapping("/login").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type","Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//conseguir token
				registry.addMapping("/auth/token").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//user
				registry.addMapping("/user").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type","Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//check email
				registry.addMapping("/users/{email}").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type","Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//check username
				registry.addMapping("/usernames/{username}").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type","Authorization", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
			}

		};
	}

//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
}