package com.hkq.mtspring.beans;

public class BeanReference {
	// bean id
	private String name;
	
	private Object bean;
	
	public BeanReference(String name) {
		this.name = name;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public String getName() {
		return name;
	}
}
