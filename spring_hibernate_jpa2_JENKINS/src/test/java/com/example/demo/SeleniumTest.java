package com.example.demo;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {
	
	private WebDriver driver;
	
	@BeforeEach
	void init() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
	}
	
	@Test
	public void Pantalla() {
		driver.get("http://localhost:8288/ProyectoHibernate2/");
		WebElement user = driver.findElement(By.id("nombreUser"));
		WebElement password = driver.findElement(By.id("contrasena"));
		WebElement boton = driver.findElement(By.className("btn"));
		
		user.sendKeys("loli");
		password.sendKeys("loli123");
		boton.click();
		
		String titulo = driver.getCurrentUrl();
		
		assertTrue(titulo.equals("http://localhost:8288/ProyectoHibernate2/login/submit"));
	}
	

}
