package com.hkq.mtspring.beans.reader.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hkq.mtspring.beans.BeanDefinition;
import com.hkq.mtspring.beans.BeanReference;
import com.hkq.mtspring.beans.PropertyValue;
import com.hkq.mtspring.beans.io.loader.ResourceLoader;
import com.hkq.mtspring.beans.reader.AbstractBeanDefinitionReader;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) throws Exception {
		InputStream input = getResourceLoader().getResource(location).getInputStream();
		
		doLoadDefinitions(input);
	}
	
	private void doLoadDefinitions(InputStream input) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(input);
		// 解析bean
		registerBeanDefinitions(doc);
		input.close();
	}
	
	private void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();
		parseBeanDefinitions(root);
	}
	
	private void parseBeanDefinitions(Element root) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element elem = (Element) node;
				processBeanDefinition(elem);
			}
		}
	}
	
	private void processBeanDefinition(Element elem) {
		String id = elem.getAttribute("id");
		String className = elem.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClass(className);
		
		processProperty(elem, beanDefinition);
		
		getRegistry().put(id, beanDefinition);
	}
	
	private void processProperty(Element elem, BeanDefinition beanDefinition) {
		NodeList propertyNode = elem.getElementsByTagName("property");
		for(int i = 0; i < propertyNode.getLength(); i++) {
			Node node = propertyNode.item(i);
			if(node instanceof Element) {
				Element propertyElem = (Element) node;
				String name = propertyElem.getAttribute("name");
				
				String value = propertyElem.getAttribute("value");
				String ref = propertyElem.getAttribute("ref");
				if("".equals(value) && "".equals(ref)) {
					throw new IllegalArgumentException("<property>标签中必须包含value或ref，且不能为空字符串");
				}
				if(!"".equals(value) && !"".equals(ref)) {
					throw new IllegalArgumentException("<property>标签中不能同时包含value和ref");
				}
				
				if(!"".contentEquals(value)) {
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				} else {
					BeanReference reference = new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, reference));
				}
			}	
		}
	}
}
