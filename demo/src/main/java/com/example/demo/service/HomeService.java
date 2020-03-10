package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeService {

	@RequestMapping("/")
	public String hello() {
		return "hello";
	}
	
}
