package org.evrim.spring.exam.aop2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;
import java.util.stream.Stream;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("Registered Beans:");
        Arrays.stream(configurableListableBeanFactory.getBeanDefinitionNames())
                .forEach(System.out::println);

        System.out.println("CustomBeanFactoryPostProcessor.postProcessBeanFactory completed");
    }
}
