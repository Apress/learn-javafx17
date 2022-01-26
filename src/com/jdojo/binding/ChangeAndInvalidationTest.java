// ChangeAndInvalidationTest.java
package com.jdojo.binding;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class ChangeAndInvalidationTest {	
	public static void main(String[] args) {
		IntegerProperty counter = new SimpleIntegerProperty(100);
		
		// Add an invalidation listener to the counter property
		counter.addListener(ChangeAndInvalidationTest::invalidated);
		
		// Add a change listener to the counter property
		counter.addListener(ChangeAndInvalidationTest::changed);
		
		System.out.println("Before changing the counter value-1");
		counter.set(101);
		System.out.println("After changing the counter value-1");
		
		System.out.println("\nBefore changing the counter value-2");
		counter.set(102);
		System.out.println("After changing the counter value-2");

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
	
	public static void changed(ObservableValue<? extends Number> prop, 
	                           Number oldValue, 
	                           Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);        
	}	
}
