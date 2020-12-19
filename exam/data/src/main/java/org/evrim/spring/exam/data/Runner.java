package org.evrim.spring.exam.data;

import org.evrim.spring.exam.data.dao.JdbcEmployeeDao;
import org.evrim.spring.exam.data.dao.JpaProductDao;
import org.evrim.spring.exam.data.ds.Employee;
import org.evrim.spring.exam.data.ds.Product;
import org.evrim.spring.exam.data.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        Employee employee = new Employee(10,"name","lastname","email@company.com","1111", new Date(),100);
        Product product = new Product(-1, "product1", 5, 1000, false);

        MainService mainService = context.getBean(MainService.class);
        try {
            mainService.insertRecordsTransactional(product,employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Listing Products");
        JpaProductDao jpaProductDao = context.getBean(JpaProductDao.class);
        jpaProductDao.findAll().
                forEach(System.out::println);

        System.out.println("Listing Employees");
        JdbcEmployeeDao jdbcEmployeeDao = context.getBean(JdbcEmployeeDao.class);
        jdbcEmployeeDao.getEmployees().
                forEach(System.out::println);


    }
}
