// SimpleListChangeTest.java
package com.jdojo.collections;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class SimpleListChangeTest {
	public static void main(String[] args) {
		// Create an observable list
		ObservableList<String> list = FXCollections.observableArrayList();
		
		// Add a change listener to the list
		list.addListener(SimpleListChangeTest::onChanged);
		
		// Manipulate the elements of the list
		list.add("one");
		list.add("two");
		FXCollections.sort(list);
		list.clear();
	}
	
	public static void onChanged(ListChangeListener.Change<? extends String> change) {
		System.out.println("List has changed");
	}
}
