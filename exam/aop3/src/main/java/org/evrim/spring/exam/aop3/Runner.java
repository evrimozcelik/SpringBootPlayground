package org.evrim.spring.exam.aop3;

import org.evrim.spring.exam.aop3.beans.IOrderManager;
import org.evrim.spring.exam.aop3.data.Order;
import org.evrim.spring.exam.aop3.data.OrderItem;
import org.evrim.spring.exam.aop3.data.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        IOrderManager orderManager = context.getBean(IOrderManager.class);

        Order order = new Order(1, "customer1",
                Arrays.asList(
                        new OrderItem(new Product(1,"product1"),1),
                        new OrderItem(new Product(2,"product2"),5)
                ));

        orderManager.processOrder(order);

    }
}
