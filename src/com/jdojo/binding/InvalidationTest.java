// InvalidationTest.java
package com.jdojo.binding;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InvalidationTest {
	public static void main(String[] args) {
		IntegerProperty counter = new SimpleIntegerProperty(100);

		// Add an invalidation listener to the counter property
		counter.addListener(InvalidationTest::invalidated);

		System.out.println("Before changing the counter value-1");
		counter.set(101);
		System.out.println("After changing the counter value-1");

		/* At this point counter property is invalid and further changes
		   to its value will not generate invalidation events.
		 */
		System.out.println("\nBefore changing the counter value-2");
		counter.set(102);
		System.out.println("After changing the counter value-2");

		// Make the counter property valid by calling its get() method
		int value = counter.get();
		System.out.println("Counter value = " + value);

		/* At this point counter property is valid and further changes
		   to its value will generate invalidation events.
		 */
		// Try to set the same value
		System.out.println("\nBefore changing the counter value-3");
		counter.set(102);
		System.out.println("After changing the counter value-3");

		// Try to set a different value
		System.out.println("\nBefore changing the counter value-4");
		counter.set(103);
		System.out.println("After changing the counter value-4");
	}

	public static void invalidated(Observable prop) {
		System.out.println("Counter is invalid.");
	}
}
