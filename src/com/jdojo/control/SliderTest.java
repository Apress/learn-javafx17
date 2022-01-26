// SliderTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SliderTest extends Application {
	Rectangle rect = new Rectangle(0, 0, 200, 50);
	Slider redSlider = getSlider();
	Slider greenSlider = getSlider();
	Slider blueSlider = getSlider();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add a ChangeListener to all sliders
		redSlider.valueProperty().addListener(this::changed);
		greenSlider.valueProperty().addListener(this::changed);
		blueSlider.valueProperty().addListener(this::changed);

		GridPane root = new GridPane();
		root.setVgap(10);
		root.add(rect, 0, 0, 2, 1);
		root.add(new Label("Use sliders to change the fill color"), 0, 1, 2, 1);
		root.addRow(2, new Label("Red:"), redSlider);
		root.addRow(3, new Label("Green:"), greenSlider);
		root.addRow(4, new Label("Blue:"), blueSlider);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Slider Controls");
		stage.show();
		
		// Adjust the fill color of the rectangle
		changeColor();
	}

	public Slider getSlider() {
		Slider slider = new Slider(0, 255, 125);		
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(85);
		slider.setMinorTickCount(10);
		slider.setBlockIncrement(20);
		slider.setSnapToTicks(true);
		return slider;
	}

	// A change listener to track the change in color
	public void changed(ObservableValue<? extends Number> prop, 
	                    Number oldValue, 
	                    Number newValue) {
		changeColor();
	}
	
	public void changeColor() {
		int r = (int)redSlider.getValue();
		int g = (int)greenSlider.getValue();
		int b = (int)blueSlider.getValue();
		Color fillColor = Color.rgb(r, g, b);
		rect.setFill(fillColor);
	}
}
