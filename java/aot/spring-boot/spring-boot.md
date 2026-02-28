# Ahead-of-Time Processing

```
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<executions>
		<execution>
			<id>process-aot</id>
			<goals>
				<goal>process-aot</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```


## References
[Ahead of Time Optimizations](https://docs.spring.io/spring-framework/reference/core/aot.html#aot.bestpractices)


## Ограничения
Нельзя регистрировать бины через instanceSuplier и  через rtegisterSingleton
AOT генерирует исходеый код на языке Java, байткод для динамических прокси, и подсказки RuntimeHints

If custom code needs to register extra beans programmatically, make sure that custom registration code uses BeanDefinitionRegistry instead of BeanFactory as only bean definitions are taken into account.  A good pattern is to implement ImportBeanDefinitionRegistrar and register it via an @Import on one of your configuration classes. 