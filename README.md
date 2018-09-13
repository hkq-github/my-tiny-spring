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
