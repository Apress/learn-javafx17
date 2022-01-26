// CircleArea.java
package com.jdojo.binding;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CircleArea {
	public static void main(String[] args) {
		DoubleProperty radius = new SimpleDoubleProperty(7.0);

		// Create a binding for computing arae of the circle
		DoubleBinding area = radius.multiply(radius).multiply(Math.PI);

		System.out.println("Radius = " + radius.get() + 
		                   ", Area = " + area.get());

		// Change the radius
		radius.set(14.0);
		System.out.println("Radius = " + radius.get() + 
		                   ", Area = " + area.get());

		// Create a DoubleProperty and bind it to an expression
		// that computes the area of the circle
		DoubleProperty area2 = new SimpleDoubleProperty();
		area2.bind(radius.multiply(radius).multiply(Math.PI));
		System.out.println("Radius = " + radius.get() + 
		                   ", Area2 = " + area2.get());
	}
}
