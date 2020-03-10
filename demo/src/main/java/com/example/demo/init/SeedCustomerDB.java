package com.example.demo.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Component
public class SeedCustomerDB implements InitializingBean{

	@Autowired
	private CustomerRepository repo;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Adding customer1");
		repo.save(new Customer(1,"c1","c1@company.com"));
		
		System.out.println("Adding customer2");
		repo.save(new Customer(2,"c2","c2@company.com"));
	}

}
