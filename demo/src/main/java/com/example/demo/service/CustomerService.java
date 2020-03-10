package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	@GetMapping
	public List<Customer> customers() {
		List<Customer> list = new ArrayList<Customer>();
		repo.findAll().forEach(list::add);
		return list;
	
	}
	
	
}
