package org.evrim.spring.exam.aop3.beans;

import org.evrim.spring.exam.aop3.data.Order;

public interface IOrderManager extends INotifyCustomer{

    int processOrder(Order order);

}
