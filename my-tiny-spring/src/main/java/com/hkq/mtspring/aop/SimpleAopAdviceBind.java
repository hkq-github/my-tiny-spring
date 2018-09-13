package com.hkq.mtspring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hkq.mtspring.aop.advice.Advice;

public class SimpleAopAdviceBind implements InvocationHandler, AopAdviceBind {
	private Object source;
	private Advice advice;
	
	public SimpleAopAdviceBind() {

	}
	
	@Override
	public Object bind(Object source, Advice advice) {
		this.source = source;
		this.advice = advice;
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(), 
				source.getClass().getInterfaces(), 
				this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			advice.before();
			result = method.invoke(source, args);
			advice.afterReturning();
		} catch(Exception ex) {
			advice.afterThrowing();
			throw ex;
		} finally {
			advice.after();
		}
		
		return result;
	}
}
