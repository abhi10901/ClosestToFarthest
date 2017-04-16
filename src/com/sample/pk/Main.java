package com.sample.pk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	public static Coordinate origin;

	public static void main(String args[]) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Tree tree = new Tree();
		
		try {
			System.out.print("Enter origin x : ");
			int x = scanner.nextInt();

			System.out.print("\nEnter origin y : ");
			int y = scanner.nextInt();

			System.out.printf("\nOrigin is (%d, %d)%n%n", x, y);
			origin = createCoordinate("Origin", x, y);
		} catch (InputMismatchException e) {
			System.out.println("Not a valid number.");
			return;
		}
		
		readCoordinatesFromFile(tree);

		List<Coordinate> finalList = new ArrayList<>();
		Tree.inOrder(tree.getRoot(), finalList);

		writeCoordinatesToFile(finalList);
		System.out.println("Rearranged coordinates : ");
		finalList.stream().forEach(coordinate -> System.out.println(coordinate.toString()));

	}

	/**
	 * This will create binary search tree using given input json file.
	 * 
	 * @param tree - Tree
	 */
	private static void readCoordinatesFromFile(Tree tree) {
		JSONParser parser = new JSONParser();

		try {
			Object object = parser.parse(new FileReader("coordinates.json"));
			JSONArray jArray = (JSONArray) object;

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> itr = jArray.iterator();
			while (itr.hasNext()) {
				JSONObject jObj = itr.next();
				String id = (String) jObj.get("id");
				String coordinate = (String) jObj.get("value");
				int cx = Integer.valueOf(coordinate.split(",")[0]);
				int cy = Integer.valueOf(coordinate.split(",")[1]);

				Coordinate point = createCoordinate(id, cx, cy);
				Node node = createNode(point);
				tree.insert(node);

			}
		} catch (ParseException e) {
			System.out.println("Can not parse given json file. Not a valid format json.");
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number.");
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found in currnet working directory.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write rearranged coordinates list into output file by adding all each coordinate
	 * into JSONArray.
	 * 
	 * @param coordinates - List of rearranged Coordinate
	 */
	@SuppressWarnings("unchecked")
	private static void writeCoordinatesToFile(List<Coordinate> coordinates) {
		JSONArray jArray = new JSONArray();
		
		for(Coordinate coordinate : coordinates) {
			JSONObject obj = new JSONObject();
			obj.put("id", coordinate.getId());
			obj.put("value", ""+coordinate.getX()+","+coordinate.getY()+"");
			
			jArray.add(obj);
		}
		FileOutputStream ostream = null;
		try {
			ostream = new FileOutputStream(new File("closestCoordinates.json"));
			ostream.write(jArray.toJSONString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ostream != null)
				try {
					ostream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}

	/**
	 * Find the distance of given coordinate from origin using 
	 * pythagorean theorem.
	 * 
	 * @param point - Coordinate
	 * @return distance - Double
	 */
	private static double findDistanceFromOrigin(Coordinate point) {
		int xDiff = point.getX() - origin.getX();
		int yDiff = point.getY() - origin.getY();

		return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
	}

	/**
	 * Create instance of Coordinate based on the given x,y value and name of
	 * the coordinate.
	 * 
	 * @param id - String name of the coordinate
	 * @param x - int - x value of coordinate
	 * @param y - int - y value of coordinate
	 * @return Coordinate instance
	 */
	private static Coordinate createCoordinate(String id, int x, int y) {
		return new Coordinate(id, x, y);
	}

	/**
	 * This method find the distance of given coordinate from origin and
	 * create new node using this distance and the provided Coordinate.
	 * 
	 * @param coordinate - Instance of Coordinate contains x and y value of 
	 * 						the point
	 * @return node - Instance of Node 
	 */
	private static Node createNode(Coordinate coordinate) {
		Double distance = findDistanceFromOrigin(coordinate);
		return new Node(coordinate, distance);
	}

}
