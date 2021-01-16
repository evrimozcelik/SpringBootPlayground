package org.evrim.spring.exam.aop2;

import org.evrim.spring.exam.aop2.beans.ParentBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //context.getEnvironment().setActiveProfiles("first");
        context.register(ApplicationConfig.class);
        context.refresh();

        context.registerShutdownHook();

        ParentBean bean = context.getBean(ParentBean.class);

    }
}
