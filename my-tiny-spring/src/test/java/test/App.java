package test;

import test.services.HelloServices;

public class App {
	
	private HelloServices helloServices;
	
	public void doSomething(String user) {
		helloServices.sayHello(user);
	}
	
	public void setHelloServices(HelloServices helloServices) {
		this.helloServices = helloServices;
	}
}
