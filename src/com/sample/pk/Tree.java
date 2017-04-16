package com.sample.pk;

import java.util.List;

public class Tree {

	private Node root;
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Tree() {
		root = null;
	}
	
	/**
	 * Insert new node into the current tree to form binary search tree.
	 * 
	 * @param node - new node to be inserted
	 */
	public void insert(Node node) {
		if(root == null) {
			root = node;
		}
		else {
			Double distance = node.getDistance();
			Node current = root;
			Node parent;
			
			while(true) {
				parent = current;
				if(distance.compareTo(current.getDistance()) < 0) {
					current = current.getLeft();
					
					if(current == null) {
						parent.setLeft(node);
						return;
					}
				} else {
					current = current.getRight();
					
					if(current == null) {
						parent.setRight(node);
						break;
					}
				}
			}
			
		}
	}
	
	/**
	 * This will traverse given tree in inorder traversal, get all node coordinate
	 * and add them into the finalList.
	 * 
	 * @param root - Root of the tree
	 * @param finalList - List to store all traversed coordinates in inorder traversal
	 */
	public static void inOrder(Node root, List<Coordinate> finalList) {
		if(root != null) {
			inOrder(root.getLeft(), finalList);
			finalList.add(root.getCoordinate());
			inOrder(root.getRight(), finalList);
		}
	}
}
