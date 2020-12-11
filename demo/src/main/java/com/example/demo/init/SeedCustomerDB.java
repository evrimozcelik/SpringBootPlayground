package com.example.demo.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Component
public class SeedCustomerDB {

	@Bean
	InitializingBean seedDatabase(CustomerRepository repo) {
		return () -> {
			System.out.println("Adding customer1");
			repo.save(new Customer(1,"customer1","customer1@company.com"));
			
			System.out.println("Adding customer2");
			repo.save(new Customer(2,"customer2","customer2@company.com"));
		};
	}

}
