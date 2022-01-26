// BindingsClassTest.java
package com.jdojo.binding;

import java.util.Locale;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class BindingsClassTest {
	public static void main(String[] args) {
		DoubleProperty radius = new SimpleDoubleProperty(7.0);
		DoubleProperty area = new SimpleDoubleProperty(0.0);
	
		// Bind area to an expression that computes the area of the circle
		area.bind(Bindings.multiply(Bindings.multiply(radius, radius), Math.PI));

		// Create a string expression to describe the circle	    
		StringExpression desc = Bindings.format(Locale.US, 
		                          "Radius = %.2f, Area = %.2f", radius, area);

		System.out.println(desc.get());

		// Change the radius
		radius.set(14.0);
		System.out.println(desc.getValue());
	}
}
