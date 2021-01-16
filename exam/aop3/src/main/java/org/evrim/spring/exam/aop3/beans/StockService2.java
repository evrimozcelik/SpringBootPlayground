package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("second")
public class StockService2 implements IStockService {
    @Override
    public int sendItems(Order order) {
        System.out.println("StockService2.sendItems");
        return 0;
    }
}
