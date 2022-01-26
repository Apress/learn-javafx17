// ColorPickerTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorPickerTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorPicker bgColor = new ColorPicker(Color.RED);

		// A Rectangle to show the selected color from the color picker
		Rectangle rect = new Rectangle(0, 0, 100, 50);
		rect.setFill(bgColor.getValue());
		rect.setStyle("-fx-stroke-width: 2; -fx-stroke: black;");
		
		// Add an ActionEvent handler to the ColorPicker, so we change
		// the fill color for the rectangle when we pick a new color
		bgColor.setOnAction(e -> rect.setFill(bgColor.getValue()));
	
		HBox root = new HBox(new Label("Color:"), bgColor, rect);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);      
		stage.setScene(scene);      
		stage.setTitle("Using ColorPicker Controls");
		stage.show();
	}
}
