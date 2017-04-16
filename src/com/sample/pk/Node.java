package com.sample.pk;

public class Node {

	private Coordinate coordinate;
	private Double distance;
	private Node left = null;
	private Node right = null;

	public Node() {
		this.coordinate = null;
		this.distance = null;
	}

	public Node(Coordinate coordinate, Double distance) {
		this.coordinate = coordinate;
		this.distance = distance;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
