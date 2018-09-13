package com.hkq.mtspring.aop.advice;

/*
try {
	before();
	目标方法
	afterReturning();
} catch(Exception ex) {
	afterThrowing();
	throw ex;
} finally {
	after();
}
*/
public interface Advice {
	/**
	 * 在方法被调用前执行
	 */
	void before();
	
	/**
	 * 在方法成功执行后执行
	 */
	void afterReturning();
	
	/**
	 * 在方法抛出后调用
	 */
	void afterThrowing();
	
	/**
	 * 方法执行完成后执行，无论是否发生异常
	 */
	void after();
}
