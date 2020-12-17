package org.evrim.spring.exam.data;

import org.evrim.spring.exam.data.dao.JdbcEmployeeDao;
import org.evrim.spring.exam.data.dao.JpaProductDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        JdbcEmployeeDao jdbcEmployeeDao = context.getBean(JdbcEmployeeDao.class);

        System.out.println("Employees");
        jdbcEmployeeDao.getEmployeeEmails().
                forEach(System.out::println);

        jdbcEmployeeDao.getEmployees().
                forEach(System.out::println);

        System.out.println("Products");
        JpaProductDao jpaProductDao = context.getBean(JpaProductDao.class);
        jpaProductDao.findAll().
                forEach(System.out::println);

    }
}
