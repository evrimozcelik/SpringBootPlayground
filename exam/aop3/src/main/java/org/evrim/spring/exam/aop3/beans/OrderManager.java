package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.annotations.InTransaction;
import org.evrim.spring.exam.aop3.annotations.Loggable;
import org.evrim.spring.exam.aop3.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Loggable
public class OrderManager implements IOrderManager {

    @Autowired
    OrderService orderService;

    @Autowired
    IPaymentService paymentService;

    @Autowired
    IStockService stockService;

    @Override
    @InTransaction
    public int processOrder(Order order) {
        orderService.saveOrder(order);
        paymentService.getPayment(order);
        stockService.sendItems(order);
        return 0;
    }
}
