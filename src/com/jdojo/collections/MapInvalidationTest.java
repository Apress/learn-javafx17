// MapInvalidationTest.java
package com.jdojo.collections;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class MapInvalidationTest {
	public static void main(String[] args) {
		ObservableMap<String, Integer> map = FXCollections.observableHashMap();

		// Add an InvalidationListener to the map
		map.addListener(MapInvalidationTest::invalidated);

		System.out.println("Before adding (\"one\", 1)");
		map.put("one", 1);
		System.out.println("After adding (\"one\", 1)");

		System.out.println("\nBefore adding (\"two\", 2)");
		map.put("two", 2);
		System.out.println("After adding (\"two\", 2)");

		System.out.println("\nBefore adding (\"one\", 1)");
		
		// Adding the same (key, value) does not trigger an invalidation event
		map.put("one", 1);
		System.out.println("After adding (\"one\", 1)");

		System.out.println("\nBefore adding (\"one\", 100)");
		
		// Adding the same key with different value triggers invalidation event
		map.put("one", 100);
		System.out.println("After adding (\"one\", 100)");

		System.out.println("\nBefore calling clear()");
		map.clear();
		System.out.println("After calling clear()");
	}

	public static void invalidated(Observable map) {
		System.out.println("Map is invalid.");
	}
}
