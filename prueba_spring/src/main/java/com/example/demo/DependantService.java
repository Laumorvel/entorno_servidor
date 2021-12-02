package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependantService {
/*
	private final Service1 service1;
	private final Service2 service2;
*/
	
	private Service1 service1;
	private Service2 service2;

	/*
	 * @Autowired public DependantService(Service1 service1, Service2 service2) {
	 * this.service1 = service1; this.service2 = service2; }
	 */

	void doSmth() {
		service1.doSmth();
		service2.doSmth();
	}

	@Autowired
	public void setService1(Service1 service1) {
		this.service1 = service1;
	}

	@Autowired
	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

}
