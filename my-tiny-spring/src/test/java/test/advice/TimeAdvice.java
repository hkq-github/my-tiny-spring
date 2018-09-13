package test.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hkq.mtspring.aop.advice.CommonAdvice;

public class TimeAdvice extends CommonAdvice {
	
	private static final String format = "yyyy-MM-dd HH:mm:ss:s";
	
	@Override
	public void before() {
		System.out.println("Before " + new SimpleDateFormat(format).format(new Date()));
	}
	
	
	@Override
	public void afterReturning() {
		System.out.println("After " + new SimpleDateFormat(format).format(new Date()));
	}
	
	@Override
	public void after() {
		System.out.println("After...");
	}
}
