package org.evrim.spring.exam.aop3.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Data
public class Order {
    int id;
    String customer;
    List<OrderItem> orderItems;
}
