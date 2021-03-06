package org.evrim.spring.exam.aop3.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class OrderItem {
    Product product;
    int quantity;
}
