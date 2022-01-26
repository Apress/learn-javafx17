// StackPaneTest.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Rectangle and a Text
		Rectangle rect = new Rectangle(200, 50);
		rect.setStyle("-fx-fill: lavender;" +
		              "-fx-stroke-type: inside;" +
		              "-fx-stroke-dash-array: 5 5;" +
		              "-fx-stroke-width: 1;" +
		              "-fx-stroke: black;" +
		              "-fx-stroke-radius: 5;");

		Text text = new Text("A Rectangle");
		
		// Create a StackPane with a Rectangle and a Text
		StackPane root = new StackPane(rect, text);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using StackPane");
		stage.show();
	}
}
