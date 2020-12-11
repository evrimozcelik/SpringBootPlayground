package com.example.demo.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;

@Component
public class SeedCarDB implements InitializingBean {
	public static final Point BEYLIKDUZU = new Point(40.9941306,28.648797);
	public static final Point SARIYER = new Point(41.165895,29.0400142);
	public static final Point MASLAK = new Point(41.1126293,29.0073562);
	
	@Autowired
	private CarRepository repo;

	@Override
	public void afterPropertiesSet() throws Exception {
		repo.deleteAll();
		
		System.out.println("Adding car1");
		repo.save(new Car("car1", "bmw", "316", BEYLIKDUZU));
		
		System.out.println("Adding car2");
		repo.save(new Car("car2", "mercedes", "D180", SARIYER));
		
		System.out.println("Adding car3");
		repo.save(new Car("car3", "mercedes", "D180", SARIYER));
		
	}

}
