package org.evrim.spring.exam.aop3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:/application.properties")
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
