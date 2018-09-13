package test.advice;

import com.hkq.mtspring.aop.advice.CommonAdvice;

public class ExceptionAdvice extends CommonAdvice{
	
	@Override
	public void afterThrowing() {
		System.out.println("Boo..Exception.......");
	}
	
}
