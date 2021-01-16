package org.evrim.spring.exam.data2;

import org.evrim.spring.exam.data2.dao.JdbcEmployeeDao;
import org.evrim.spring.exam.data2.dao.JdbcProductDao;
import org.evrim.spring.exam.data2.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        CompanyService companyService = context.getBean(CompanyService.class);

        companyService.printEmployeeInfo();

        System.out.println("--------");
        companyService.printProductInfo();

        System.out.println("--------");
        try {
            companyService.registerData();
        } catch (Exception e) {
            System.err.println("\nException: " + e.getMessage());
        }


        System.out.println("--------");
        companyService.printEmployeeInfo();

        System.out.println("--------");
        companyService.printProductInfo();

    }
}
