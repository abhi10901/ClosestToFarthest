### Coordinates rearrange from closest to farthest.

## Steps to execute program Main class file. 

1) Place input coordinate.json file in the project directory. I already placed sample json file in project folder.

2) Need to execute Main.java class file:
	a) Open this project in your favorite IDE and update maven dependencies 
		project right click > Maven > Update Project ==> this will resolve all maven dependencies those are needed tu run this project
		
	b) Run Main.java file Run as > Java Application
		
		
Note: While running above Java file it will ask for x and y coordinate of origin and based on origin program will process coordinates.json file, calculate distance from origin for each given points in json file and generate Binary search Tree Data Structure. Once done processing input json file, program will create output json file in current working directory named closestCoordinates.json, traverse binary search tree in inorder traversal and write into the output file.