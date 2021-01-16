package org.evrim.spring.exam.data2.service;

import org.evrim.spring.exam.data2.dao.JdbcEmployeeDao;
import org.evrim.spring.exam.data2.dao.JdbcProductDao;
import org.evrim.spring.exam.data2.ds.Employee;
import org.evrim.spring.exam.data2.ds.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Stream;

@Service
public class CompanyService {

    @Autowired
    JdbcEmployeeDao jdbcEmployeeDao;

    @Autowired
    JdbcProductDao jdbcProductDao;

    public void printEmployeeInfo() {
        System.out.println("Printing all employees:");
        jdbcEmployeeDao.getAllEmployees().forEach(System.out::println);

        System.out.println("Printing average salary: " + jdbcEmployeeDao.calculateAverageSalary());

        System.out.println("Printing emails:");
        jdbcEmployeeDao.getEmployeeEmails().forEach(System.out::println);

        System.out.println("Printing employee: " + jdbcEmployeeDao.getEmployee(4));
    }

    public void printProductInfo() {
        System.out.println("Printing all products:");
        jdbcProductDao.getAllProducts().forEach(System.out::println);
    }


    @Transactional
    public void registerData() {
        System.out.println("Registering data:");
        registerEmployees(10);
        registerEmployees(11);
        registerProduct(-1);
        registerProduct(11);
    }

    private void registerEmployees(int id) {
        jdbcEmployeeDao.insertEmployee(
                new Employee(id,"name"+id,"lastname" + id,"email@company.com" + id,"1111" + id, new Date(),100)
        );
        System.out.println(String.format("Employee %d registered", id));
    }

    private void registerProduct(int id) {
        jdbcProductDao.insertProduct(
                new Product(id, "product"+id, 5+id, 1000, false)
        );
        System.out.println(String.format("Product %d registered", id));
    }



}
