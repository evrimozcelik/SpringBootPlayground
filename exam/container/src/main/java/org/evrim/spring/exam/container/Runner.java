package org.evrim.spring.exam.container;


import org.evrim.spring.exam.container.beans.UserContext;
import org.evrim.spring.exam.container.service.IService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Hello From Spring Framework");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        IService service = context.getBean(IService.class);

        String response = service.process("request");

        System.out.println("Response:" + response);

    }
}
