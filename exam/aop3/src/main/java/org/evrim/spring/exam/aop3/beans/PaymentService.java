package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Override
    public int getPayment(Order order) {
        System.out.println("PaymentService.getPayment");
        return 0;
    }
}
