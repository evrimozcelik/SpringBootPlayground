package org.evrim.spring.exam.data2.ds;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private int quantity;
    private float price;
    private boolean available;

    public Product() {
        super();
    }

}
