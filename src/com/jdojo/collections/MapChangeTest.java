// MapChangeTest.java
package com.jdojo.collections;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class MapChangeTest {
	public static void main(String[] args) {
		ObservableMap<String, Integer> map = FXCollections.observableHashMap();

		// Add an MapChangeListener to the map
		map.addListener(MapChangeTest::onChanged);
		
		System.out.println("Before adding (\"one\", 1)");
		map.put("one", 1);	    
		System.out.println("After adding (\"one\", 1)");
		
		System.out.println("\nBefore adding (\"two\", 2)");
		map.put("two", 2);
		System.out.println("After adding (\"two\", 2)");
		
		System.out.println("\nBefore adding (\"one\", 3)");
		
		// Will remove ("one", 1) and add("one", 3)
		map.put("one", 3);
		System.out.println("After adding (\"one\", 3)");
		
		System.out.println("\nBefore calling clear()");
		map.clear();
		System.out.println("After calling clear()");
	}
	
	public static void onChanged(
			MapChangeListener.Change<? extends String, ? extends Integer> change) {
		if (change.wasRemoved()) {
			System.out.println("Removed (" + change.getKey() + ", " + 
			                    change.getValueRemoved() + ")"); 
		}
		
		if (change.wasAdded()) {		    
			System.out.println("Added (" + change.getKey() + ", " + 
			                   change.getValueAdded() + ")");	
		}
	}
}
