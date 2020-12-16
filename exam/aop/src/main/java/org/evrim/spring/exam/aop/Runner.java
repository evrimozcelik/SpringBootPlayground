package org.evrim.spring.exam.aop;

import org.evrim.spring.exam.aop.beans.MyBean;
import org.evrim.spring.exam.aop.services.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        MyService service = context.getBean(MyService.class);
        MyBean bean = context.getBean(MyBean.class);
        bean.setName("evrim");
        bean.setSurname("ozcelik");

        service.register(bean);

    }
}
