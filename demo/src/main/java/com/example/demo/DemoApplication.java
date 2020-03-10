package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.example.demo.config.Settings;

@SpringBootApplication
public class DemoApplication {
	
	// Configuration
	
	@Value("${configuration.projectName}")
	void setProjectName(String projectName) {
		System.out.println("setting project name: " + projectName);
	}

	@Autowired
	void setEnvironment(Environment env) {
		System.out.println("setting environment: " + env.getProperty("configuration.projectName"));
	}
	
	@Autowired
	void setConfiguration(Settings conf) {
		System.out.println("setting configuration: " + conf.getProjectName());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
