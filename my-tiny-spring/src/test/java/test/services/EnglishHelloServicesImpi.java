package test.services;

public class EnglishHelloServicesImpi implements HelloServices {
	
	private String message;
	
	@Override
	public void sayHello(String name) {
		System.out.println(message + " " + name + ".");
		throw new RuntimeException("lalalalall");
	}
	
}
