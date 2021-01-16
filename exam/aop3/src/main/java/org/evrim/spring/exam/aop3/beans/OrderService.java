package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderService {
    int saveOrder(Order order) {
        System.out.println("OrderService.saveOrder");
        System.out.println("OrderId: " + order.getId());
        System.out.println("Customer: " + order.getCustomer());
        order.getOrderItems().forEach(s -> {
            System.out.println("OrderLine: " + s.getProduct() + " x " + s.getQuantity());
        });
        return 0;
    }
}
