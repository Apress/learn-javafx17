// BoundProperty.java
package com.jdojo.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoundProperty {
	public static void main(String[] args) {
		IntegerProperty x = new SimpleIntegerProperty(10);
		IntegerProperty y = new SimpleIntegerProperty(20);
		IntegerProperty z = new SimpleIntegerProperty(60);
		z.bind(x.add(y));
		System.out.println("After binding z: Bound = " + z.isBound() + 
		                   ", z = " + z.get());

		// Change x and y
		x.set(15);
		y.set(19);
		System.out.println("After changing x and y: Bound = " + z.isBound() + 
		                   ", z = " + z.get());
		// Unbind z
		z.unbind();

		// Will not affect the value of z as it is not bound to x and y anymore
		x.set(100);
		y.set(200);
		System.out.println("After unbinding z: Bound = " + z.isBound() + 
		                   ", z = " + z.get());
	}
}
