package org.evrim.spring.exam.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({ApplicationProperties.class})
public class Runner implements CommandLineRunner {

    @Autowired
    ApplicationProperties applicationProperties;

    @Value("${app.propertyA}")
    String propertyA;

    @Autowired
    Environment environment;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = Logger.getLogger("org.evrim");

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Application started");

        logger.log(Level.SEVERE, "Severe log");
        logger.log(Level.WARNING, "Warning log");
        logger.log(Level.INFO, "Info log");
        logger.log(Level.FINE, "Fine log");
        logger.log(Level.FINEST, "Finest log");


        System.out.println(applicationProperties);

        System.out.println("PropertyA from env: " + environment.getProperty("app.propertyA"));

        System.out.println("PropertyA from value: " + propertyA);

        jdbcTemplate.queryForList("select email from employee", String.class)
                .forEach(System.out::println);
    }
}
