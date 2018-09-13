package com.hkq.mtspring.beans.reader;

/**
 * 从配置中读取BeanDefinition
 * 
 * @author hkq
 *
 */
public interface BeanDefinitionReader {
	
	void loadBeanDefinitions(String location) throws Exception;
	
}
