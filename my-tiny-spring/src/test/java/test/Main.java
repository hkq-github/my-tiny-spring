package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hkq.mtspring.aop.advice.Advice;
import com.hkq.mtspring.context.ApplicationContext;
import com.hkq.mtspring.context.ClassPathXmlApplicationContext;

import test.advice.ExceptionAdvice;
import test.advice.TimeAdvice;

public class Main {
	
	public static final String config = "applicationContext.xml";
	
	public static void main(String[] args) throws Exception {
		
		List<Advice> advices = new ArrayList<>();
		advices.add(new TimeAdvice());
		advices.add(new ExceptionAdvice());
		Map<String, List<Advice>> beanAdvices = new HashMap<>();
		beanAdvices.put("englishHelloServices", advices);
		beanAdvices.put("chineseHelloServices", advices);
		
		ApplicationContext context = new ClassPathXmlApplicationContext(config, beanAdvices);		
		
		App app = (App) context.getBean("app");
		app.doSomething("hkq");
	}
}
