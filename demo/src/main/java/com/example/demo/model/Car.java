package com.example.demo.model;

import javax.persistence.Id;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Car {
	
	@Id
	private String id;
	private String make;
	private String model;
	
	@GeoSpatialIndexed
	private Point position;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(String id, String make, String model, Point position) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.position = position;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	
	
	
	

}
