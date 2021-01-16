package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;

public interface IStockService extends INotifyCustomer {
    int sendItems(Order order);
}
