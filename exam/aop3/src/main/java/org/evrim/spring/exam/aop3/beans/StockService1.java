package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("first")
public class StockService1 implements IStockService {
    @Override
    public int sendItems(Order order) {
        System.out.println("StockService1.sendItems");
        return 0;
    }
}
