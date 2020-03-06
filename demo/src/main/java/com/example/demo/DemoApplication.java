package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {
	
	@Value("${configuration.projectName}")
	void setProjectName(String projectName) {
		System.out.println("setting project name: " + projectName);
	}

	@Autowired
	void setEnvironment(Environment env) {
		System.out.println("setting environment: " + env.getProperty("configuration.projectName"));
	}
	
	@Autowired
	void setConfiguration(Configuration conf) {
		System.out.println("setting configuration: " + conf.getProjectName());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
