package com.hkq.mtspring.aop;

import com.hkq.mtspring.aop.advice.Advice;

/**
 * 将bean与Advice绑定，生成绑定后的代理类
 */
public interface AopAdviceBind {
	
	/**
	 * 将source与advice绑定后，返回生成的代理类
	 */
    Object bind(Object source, Advice advice);
}
