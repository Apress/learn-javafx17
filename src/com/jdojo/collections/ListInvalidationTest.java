// ListInvalidationTest.java
package com.jdojo.collections;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListInvalidationTest {
	public static void main(String[] args) {
		// Create a list with some elements
		ObservableList<String> list = 
			FXCollections.observableArrayList("one", "two");
		
		// Add an InvalidationListener to the list
		list.addListener(ListInvalidationTest::invalidated);
		
		System.out.println("Before adding three."); 
		list.add("three");
		System.out.println("After adding three."); 
		
		System.out.println("Before adding four and five."); 
		list.addAll("four", "five");
		System.out.println("Before adding four and five."); 
		
		System.out.println("Before replacing one with one."); 
		list.set(0, "one");
		System.out.println("After replacing one with one.");	
	}
	
	public static void invalidated(Observable list) {
		System.out.println("List is invalid.");	
	}
}
