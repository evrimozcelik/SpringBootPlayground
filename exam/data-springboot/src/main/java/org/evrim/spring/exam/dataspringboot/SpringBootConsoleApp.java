package org.evrim.spring.exam.dataspringboot;

import org.evrim.spring.exam.dataspringboot.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootConsoleApp implements CommandLineRunner  {

    @Autowired
    private EmployeeDao employeeDao;

    private final Logger logger = Logger.getLogger(SpringBootConsoleApp.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Printing employees");
        employeeDao.findAll().forEach(System.out::println);

        logger.info("Finding employee with email");
        employeeDao.findByEmail("Willow.Zhang@corp.com").forEach(System.out::println);

        logger.info("Finding top3 whose salary greater than");
        employeeDao.findTop3BySalaryGreaterThanOrderBySalary(90000).forEach(System.out::println);

        logger.info("Finding John");
        employeeDao.findByName("John").forEach(System.out::println);
    }
}
