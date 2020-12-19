package org.evrim.spring.exam.data.service;

import org.evrim.spring.exam.data.dao.JdbcEmployeeDao;
import org.evrim.spring.exam.data.dao.JpaProductDao;
import org.evrim.spring.exam.data.ds.Employee;
import org.evrim.spring.exam.data.ds.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainService {

    @Autowired
    private JdbcEmployeeDao jdbcEmployeeDao;

    @Autowired
    private JpaProductDao jpaProductDao;


    public void insertRecords(Product product, Employee employee) {
        System.out.println("Inserting product");
        jpaProductDao.save(product);
        System.out.println("Inserting employee");
        jdbcEmployeeDao.insertEmployee(employee);

        if (product.getId() < 0) {
            throw new RuntimeException("Product id cannot be less than 0");
        }
    }

    @Transactional(transactionManager = "transactionManager")
    public void insertRecordsTransactional(Product product, Employee employee) {
        insertRecords(product, employee);
    }

}
