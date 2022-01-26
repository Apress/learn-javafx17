// CleanupListener.java
package com.jdojo.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CleanupListener {
	public static IntegerProperty counter = new SimpleIntegerProperty(100);

	public static void main(String[] args) {
		// Add a change listener to the property
		ChangeListener<Number> listener = CleanupListener::changed;
		counter.addListener(listener);

		// Change the counter value
		counter.set(200);

		// Remove the listener
		counter.removeListener(listener);

		// Will not fire change event as change listener has 
		// already been removed.
		counter.set(300);
	}

	public static void changed(ObservableValue<? extends Number> prop,
	                           Number oldValue,
	                           Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);
	}
}
