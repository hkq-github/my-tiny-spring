package com.hkq.mtspring.beans.factory;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import com.hkq.mtspring.beans.BeanDefinition;
import com.hkq.mtspring.beans.BeanReference;
import com.hkq.mtspring.beans.PropertyValue;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
	
	@Override
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefine) throws Exception {
		
		for(PropertyValue propertyValue : beanDefine.getPropertyValues().getPropertyValueList()) {
			
			String name = propertyValue.getName();		// 注入属性名
			Object value = propertyValue.getValue();	// 注入属性值
			
			if(value instanceof BeanReference) {
				BeanReference reference = (BeanReference) value;
				value = getBean(reference.getName());
			}
			// else propertyValue is String
			
			// java bean 默认为 set + propertyName第一个字母大写 + propertyName其他字母
			String methodName = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);			
			
			try {	// 使用反射调用setter方法注入属性
				Method method = bean.getClass().getDeclaredMethod(methodName, value.getClass());
				method.setAccessible(true);
				method.invoke(bean, value);
			} catch (NoSuchMethodException ex) { 
				try { // 使用反射直接给相应字段赋值
					Field field = bean.getClass().getDeclaredField(name);
					field.setAccessible(true);
					field.set(bean, value);		
				} catch (NoSuchFieldException e) { // 注入属性失败
					new RuntimeException(bean.getClass().getName() + "注入属性" + name + "失败");
				}
			}
		}
	}
}
