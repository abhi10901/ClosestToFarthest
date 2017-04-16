package com.sample.pk;

public class Coordinate {

	private String id;
	private int x;
	private int y;
	
	public Coordinate() {}

	public Coordinate(String id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "{id: " + id + 
				", x: "+ x +
				", y: "+ y +"}";
	}
}
