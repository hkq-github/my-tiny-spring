# my-tiny-spring

**Ioc部分完全参考[tiny-spring code4craft](https://github.com/code4craft/tiny-spring)，在此基础上添加了Aop，支持在类级别（在类的每个方法）上添加`before after after-returning afterthrowning`通知**

## Ioc部分
#### 如何使用
使用xml配置文件，如声明一个bean `<bean id=" " class=" " >`, 注入属性 `<property name="propertyName" value|ref = "String or bean">`。下面是一个例子：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <!-- 注入String属性 -->
    <bean id="chineseHelloServices" class="test.services.ChineseHelloServicesImpi" >
        <property name="message" value="你好"></property>
    </bean>
    
    <bean id="englishHelloServices" class="test.services.EnglishHelloServicesImpi" > 
        <property name="message" value="Hello"></property>
    </bean>
    
    <!-- 注入其他bean -->
    <bean id="app" class="test.App">
        <property name="helloServices" ref="englishHelloServices" />
    </bean>
    
</beans>
```

## Aop部分
#### 如何使用
通过Java程序声明：
* 创建切面：继承`CommonAdvice`类，重写相应的方法
* 创建`Map<beanId, List<Advice>>` 对象，键为注入bean的Id，值为应用在该类上的通知。

在创建Application对象时，显示指定，例如：

创建一个Advice
```
public class ExceptionAdvice extends CommonAdvice{

	@Override
	public void afterThrowing() {
		System.out.println("Boo..Exception.......");
	}
}
```

使用java程序定义，向bean织入切面
```
List<Advice> advices = new ArrayList<>();
advices.add(new TimeAdvice());
advices.add(new ExceptionAdvice());
Map<String, List<Advice>> beanAdvices = new HashMap<>();
beanAdvices.put("englishHelloServices", advices);
beanAdvices.put("chineseHelloServices", advices);
		
ApplicationContext context = new ClassPathXmlApplicationContext(config, beanAdvices);
```

## 运行
项目中包含一个例子，导入项目后，运行test.Main类即可，可以修改resource下applicationContext.xml，向App注入不同的属性。运行结果如下：
```
Before 2018-09-13 23:06:07:7
Hello hkq.
After...
Boo..Exception.......
```
