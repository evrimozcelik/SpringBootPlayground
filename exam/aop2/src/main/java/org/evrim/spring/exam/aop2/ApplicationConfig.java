package org.evrim.spring.exam.aop2;


import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;

@ComponentScan (excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*ChildBeanX"))
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public static BeanPostProcessor beanPostProcessor() {
        return new CustomBeanPostProcessor();
    }
}
