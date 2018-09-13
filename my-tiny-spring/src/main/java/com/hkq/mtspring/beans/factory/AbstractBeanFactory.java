package com.hkq.mtspring.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hkq.mtspring.aop.SimpleAopAdviceBind;
import com.hkq.mtspring.aop.advice.Advice;
import com.hkq.mtspring.beans.BeanDefinition;

import test.App;

public abstract class AbstractBeanFactory implements BeanFactory {
	
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	private final List<String> beanDefinitionNames = new ArrayList<>();
	
	private Map<String, List<Advice>> beanAdvices = new ConcurrentHashMap<>();
	
	@Override
	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if(beanDefinition == null) {
			throw new IllegalArgumentException("No bean named [" + name + "] is defined");
		}
		Object bean = beanDefinition.getBean();
		if(bean == null) {
			// 创建bean并注入属性
			bean = doCreateBean(beanDefinition);
			
			// 通过代理类，织入Advice
			List<Advice> advices = beanAdvices.get(name);
			if(advices != null) {
				bean = doAddAdvice(bean, advices);
			}
				
			beanDefinition.setBean(bean);
		}
		
		return bean;
	}
	
	/** 
	 * 创建bean，并调用applyPropertyValues注入属性
	 */
	private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = beanDefinition.getBeanClass().newInstance();
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);

		return bean;
	}
	
	/**
	 * 通过JDK动态代理，向bean加入Advice，返回加入后的代理类
	 */
	private Object doAddAdvice(Object bean, List<Advice> advices) {
		Iterator<Advice> iter = advices.iterator();
		while(iter.hasNext()) {
			bean = new SimpleAopAdviceBind().bind(bean, iter.next());
		}
		return bean;
	}
	
	/**
	 * 注入属性，交由子类实现
	 */
	protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;
	
	/**
	 * 注册bean
	 */
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}
	
	/**
	 * 注册Advice
	 */
	public void registerAdvice(String beanId, List<Advice> advice) {
		if(beanId == null || advice == null) {
			throw new RuntimeException();
		}
		
		beanAdvices.put(beanId, advice);
	}
	
	/**
	 * 创建已经注册的bean
	 */
	public void preInstantiateSingletons() throws Exception {
		for(String beanName : beanDefinitionNames) {
			getBean(beanName);
		}
	}
}
