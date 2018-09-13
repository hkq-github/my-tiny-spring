package com.hkq.mtspring.beans.reader;

import java.util.HashMap;
import java.util.Map;

import com.hkq.mtspring.beans.BeanDefinition;
import com.hkq.mtspring.beans.io.loader.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
	
	private Map<String, BeanDefinition> registry = new HashMap<String, BeanDefinition>();
	
	private ResourceLoader resourceLoader;

	protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Map<String, BeanDefinition> getRegistry() {
		return registry;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
}
