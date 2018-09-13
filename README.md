# my-tiny-spring

**Ioc部分完全参考[tiny-spring code4craft](https://github.com/code4craft/tiny-spring)，在此基础上添加了Aop，支持在类级别上添加`before after after-returning afterthrowning`通知**

## Ioc部分
#### 如何使用
声明一个bean `<bean id=" " class=" " >`, 注入属性 `<property name="propertyName" value|ref = "String or bean">`。下面是一个例子：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="chineseHelloServices" class="test.services.ChineseHelloServicesImpi" >
        <property name="message" value="你好"></property>
    </bean>
    
    <bean id="englishHelloServices" class="test.services.EnglishHelloServicesImpi" > 
        <property name="message" value="Hello"></property>
    </bean>
        
    <bean id="app" class="test.App">
        <property name="helloServices" ref="englishHelloServices" />
    </bean>
    
</beans>
```

## Aop部分
#### 如何使用
通过Java程序声明：
* 继承`CommonAdvice`类，重写相应的方法
* 创建`Map<beanId, List<Advice>>` 对象，键为注入bean的Id，值为应用在该类上的通知。
在创建Application对象时，显示指定，例如：
```
List<Advice> advices = new ArrayList<>();
advices.add(new TimeAdvice());
advices.add(new ExceptionAdvice());
Map<String, List<Advice>> beanAdvices = new HashMap<>();
beanAdvices.put("englishHelloServices", advices);
beanAdvices.put("chineseHelloServices", advices);
		
ApplicationContext context = new ClassPathXmlApplicationContext(config, beanAdvices);
```
