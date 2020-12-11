package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.init.SeedCarDB;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;

@RestController
@RequestMapping("/car-service")
public class CarService {
	
	private static final Distance TWENTY_KM = new Distance(20, Metrics.KILOMETERS);

	@Autowired
	private CarRepository repo;
	
	@GetMapping
	public List<Car> cars() {
		return repo.findAll();
	}
	
	@GetMapping("/nearBy")
	public List<Car> carsNearBy() {
		List<Car> cars = new ArrayList<Car>();
		repo.findByPositionNear(SeedCarDB.SARIYER, TWENTY_KM).forEach(c -> cars.add(c.getContent()));
		return cars;
	}
	
}
