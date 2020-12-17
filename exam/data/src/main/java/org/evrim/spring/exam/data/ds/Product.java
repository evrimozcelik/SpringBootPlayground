package org.evrim.spring.exam.data.ds;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    private int id;
    private String name;
    private int quantity;
    private float price;
    private boolean available;

}
