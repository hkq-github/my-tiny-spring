package test.services;

public class ChineseHelloServicesImpi implements HelloServices {
	
	private String message;
	
	@Override
	public void sayHello(String name) {
		System.out.println(message + " " + name + "！");
	}
}
