// WeakListener.java
package com.jdojo.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

public class WeakListener {
	public static IntegerProperty counter = new SimpleIntegerProperty(100);
	public static WeakChangeListener<Number> weakListener ;
	public static ChangeListener<Number> changeListener;
	
	public static void main(String[] args) {
		// Add a weak change listener to the property
		addWeakListener();
	
		// It will fire a change event
		counter.set(300); 
		
		// Try garbage collection
		System.gc();
		
		// Check if change listener got garbage collected
		System.out.println("Garbage collected: " + 
		                    weakListener.wasGarbageCollected());
		
		// It will fire a change event
		counter.set(400);
		
		// You do not need a strong reference of the change listener
		changeListener = null; 
		
		// Try garbage collection
		System.gc();
		
		// Check if the change listener got garbage collected
		System.out.println("Garbage collected: " + 
		                    weakListener.wasGarbageCollected());
		
		// It will not fire a change event, if it was garbage collected
		counter.set(500);		
	}
	
	public static void addWeakListener() {
		// Keep a strong reference to the change listener
		changeListener = WeakListener::changed;
		
		// Wrap the change listener inside a weak change listener
		weakListener = new WeakChangeListener<>(changeListener);
		
		// Add weak change listener
		counter.addListener(weakListener);
		
		// Change the value
		counter.set(200);
	}
	
	public static void changed(ObservableValue<? extends Number> prop, 
	                           Number oldValue, 
	                           Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);
	}
}
