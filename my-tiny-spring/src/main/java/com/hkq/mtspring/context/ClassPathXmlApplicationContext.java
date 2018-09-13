package com.hkq.mtspring.context;

import java.util.List;
import java.util.Map;

import com.hkq.mtspring.aop.advice.Advice;
import com.hkq.mtspring.beans.BeanDefinition;
import com.hkq.mtspring.beans.factory.AutowireCapableBeanFactory;
import com.hkq.mtspring.beans.io.loader.ResourceLoader;
import com.hkq.mtspring.beans.io.loader.UrlResourceLoader;
import com.hkq.mtspring.beans.reader.AbstractBeanDefinitionReader;
import com.hkq.mtspring.beans.reader.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
	
	private String configLocation;
	private Map<String, List<Advice>> beanAdvices;
	
	/**
	 * 只使用Ioc功能
	 */
	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		super(new AutowireCapableBeanFactory());
		this.configLocation = configLocation;
		refresh();
	}
	
	/**
	 * 使用Ioc和Aop功能
	 */
	public ClassPathXmlApplicationContext(String configLocation, Map<String, List<Advice>> beanAdvices) throws Exception  {
		super(new AutowireCapableBeanFactory());
		this.configLocation = configLocation;
		this.beanAdvices = beanAdvices;
		refresh();
	}

	@Override
	protected void loadBeanDefinitionsAndRegister() throws Exception {
		ResourceLoader resource = new UrlResourceLoader();
		AbstractBeanDefinitionReader reader = new XmlBeanDefinitionReader(resource);
		
		reader.loadBeanDefinitions(configLocation);
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
	}

	@Override
	protected void registerBeanAdvice() {
		if(beanAdvices != null) {
			beanAdvices.forEach((k, v) -> {
				beanFactory.registerAdvice(k, v);
			});
		}
	}
}
