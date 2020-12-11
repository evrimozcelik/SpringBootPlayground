package com.example.demo.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Car;

public interface CarRepository extends MongoRepository<Car, String>{

	GeoResults<Car> findByPositionNear(Point p, Distance d);
}
