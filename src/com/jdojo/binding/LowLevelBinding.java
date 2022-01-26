// LowLevelBinding.java
package com.jdojo.binding;

import java.util.Formatter;
import java.util.Locale;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LowLevelBinding {	
	public static void main(String[] args) {
		final DoubleProperty radius = new SimpleDoubleProperty(7.0);
		final DoubleProperty area = new SimpleDoubleProperty(0);
	
		DoubleBinding areaBinding = new DoubleBinding() { 
			{
				this.bind(radius);	
			}

			@Override
			protected double computeValue() {
				double r = radius.get();
				double area = Math.PI * r *r;
				return area;
			}
		};
		
		// Bind area to areaBinding
		area.bind(areaBinding);

		// Create a StringBinding
		StringBinding desc = new StringBinding() {
			{
				this.bind(radius, area);
			}
			
			@Override
			protected String computeValue() {				
				Formatter f = new Formatter();
				f.format(Locale.US, "Radius = %.2f, Area = %.2f", 
				         radius.get(), area.get());
				String desc = f.toString();
				return desc;
			}
			
			@Override
			public ObservableList<?> getDependencies() {
				return FXCollections.unmodifiableObservableList(
							FXCollections.observableArrayList(radius, area)
						);
			}
		
			@Override
			public void dispose() {
				System.out.println("Description binding is disposed.");
			}
			
			@Override
			protected void onInvalidating() {		        
				System.out.println("Description is invalid.");
			}
		};
		
		System.out.println(desc.getValue());

		// Change the radius
		radius.set(14.0);
		System.out.println(desc.getValue());		
	}
}
