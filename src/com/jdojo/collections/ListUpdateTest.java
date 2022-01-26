// ListUpdateTest.java
package com.jdojo.collections;

import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class ListUpdateTest {
	public static void main(String[] args) {
		// Create an extractor for IntegerProperty.		
		Callback<IntegerProperty, Observable[]> extractor = (IntegerProperty p) -> {
					// Print a message to know when it is called
					System.out.println("The extractor is called for " + p);

					// Wrap the parameter in an Observable[] and return it
					return new Observable[]{p};
				};

		// Create an empty observable list with a callback to extract the
		// observable values for each element of the list
		ObservableList<IntegerProperty> list =
			FXCollections.observableArrayList(extractor);

		// Add two elements to the list
		System.out.println("Before adding two elements...");
		IntegerProperty p1 = new SimpleIntegerProperty(10);
		IntegerProperty p2 = new SimpleIntegerProperty(20);
		list.addAll(p1, p2); // Will call the call() method of the 
		// extractor - once for p1 and once for p2.
		System.out.println("After adding two elements...");

		// Add a change listener to the list
		list.addListener(ListUpdateTest::onChanged);

		// Update p1 from 10 to 100, which will trigger 
		// an update change for the list
		p1.set(100);
	}

	public static void onChanged(ListChangeListener.Change<? extends IntegerProperty> change) {
		System.out.println("List is " + change.getList());

		// Work on only updates to the list
		while (change.next()) {
			if (change.wasUpdated()) {
				// Print the details of the update
				System.out.println("An update is detected.");

				int start = change.getFrom();
				int end = change.getTo();
				System.out.println("Updated range: [" + start + ", " + end + "]");

				List<? extends IntegerProperty> updatedElementsList;
				updatedElementsList = change.getList().subList(start, end);

				System.out.println("Updated elements: " + updatedElementsList);
			}
		}
	}
}
