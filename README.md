# my-tiny-spring

**Ioc部分完全参考[tiny-spring code4craft](https://github.com/code4craft/tiny-spring)，在此基础上添加了Aop，支持在类级别上添加`before after after-returning afterthrowning`通知**

## Ioc部分
#### 如何使用
声明一个bean `<bean id=" " class=" " >`
注入属性 `<property name="propertyName" value|ref = "String or bean">`
