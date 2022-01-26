// BooelanExpressionTest.java
package com.jdojo.binding;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BooelanExpressionTest {
	public static void main(String[] args) {
		IntegerProperty x = new SimpleIntegerProperty(1);
		IntegerProperty y = new SimpleIntegerProperty(2);
		IntegerProperty z = new SimpleIntegerProperty(3);

		// Create a boolean expression for x > y && y <> z
		BooleanExpression condition = x.greaterThan(y).and(y.isNotEqualTo(z));

		System.out.println(condition.get());

		// Make the condition true by setting x to 3
		x.set(3);
		System.out.println(condition.get());
	}
}
