package org.evrim.spring.exam.container;


import org.evrim.spring.exam.container.beans.MyBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Hello From Spring Framework");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        MyBean bean = context.getBean(MyBean.class);

        System.out.println("Name:" + bean.getName());

    }
}
