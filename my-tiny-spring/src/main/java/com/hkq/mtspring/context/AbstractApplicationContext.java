package com.hkq.mtspring.context;

import com.hkq.mtspring.beans.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {
	
	protected AbstractBeanFactory beanFactory;
	
	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	/**
	 * 重要：将reader与factory结合
	 */
	public void refresh() throws Exception {
		// 读取配置文件，并向beanFactory注册bean
		loadBeanDefinitionsAndRegister(); 
		
		// 向BeanFactory注册Advice
		registerBeanAdvice();
		
		// 实例化定义的bean
		beanFactory.preInstantiateSingletons();	
	}
	
	/**
	 * 加载BeanDefinitionReader，并向BeanFactory注册
	 */
	protected abstract void loadBeanDefinitionsAndRegister() throws Exception;
	
	/**
	 * 向beanFactory注册Advice
	 */
	protected abstract void registerBeanAdvice();
	
	@Override
	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}
}
