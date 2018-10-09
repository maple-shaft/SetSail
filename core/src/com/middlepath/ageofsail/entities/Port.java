package com.middlepath.ageofsail.entities;

public class Port {

	private int id;
	private String name;
	private WorldCoordinate coordinate;
	
	private Port(int id, String name, WorldCoordinate coordinate) {
		this.setId(id);
		this.setName(name);
		this.setCoordinate(coordinate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorldCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(WorldCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	
}
