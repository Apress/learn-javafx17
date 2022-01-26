// Item.java
package com.jdojo.binding;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Item {
	private DoubleProperty weight;
	private double _weight = 150;

	public double getWeight() {
		return (weight == null)?_weight:weight.get();
	}

	public void setWeight(double newWeight) {
		if (weight == null) {
			_weight = newWeight;
		}
		else {
			weight.set(newWeight);
		}
	}

	public DoubleProperty weightProperty() {
		if (weight == null) {
			weight = new SimpleDoubleProperty(this, "weight", _weight);
		}
		return weight;
	}
}
