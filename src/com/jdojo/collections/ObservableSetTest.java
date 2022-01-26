// ObservableSetTest.java
package com.jdojo.collections;

import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class ObservableSetTest {
	public static void main(String[] args) {
		// Create an ObservableSet with three initial elements
		ObservableSet<String> s1 = FXCollections.observableSet("one", "two", "three");
		System.out.println("s1: " + s1);

		// Create a Set, and not an ObservableSet
		Set<String> s2 = new HashSet<String>();
		s2.add("one");
		s2.add("two");
		System.out.println("s2: " + s2);

		// Create an ObservableSet backed by the Set s2
		ObservableSet<String> s3 = FXCollections.observableSet(s2);
		s3.add("three");
		System.out.println("s3: " + s3);
	}
}
