package org.evrim.spring.exam.data;

import org.evrim.spring.exam.data.dao.EmployeeDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);

        employeeDao.getEmployeeEmails().
                forEach(System.out::println);

        employeeDao.getEmployees().
                forEach(System.out::println);

    }
}
