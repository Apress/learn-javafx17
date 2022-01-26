// RectangleTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// x=0, y=0, width=100, height=50, fill=LIGHTGRAY, stroke=null
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);

		// x=120, y=20, width=100, height=50, fill=WHITE, stroke=BLACK 
		Rectangle rect2 = new Rectangle(120, 20, 100, 50);
		rect2.setFill(Color.WHITE);
		rect2.setStroke(Color.BLACK);
		rect2.setArcWidth(10);
		rect2.setArcHeight(10);
	
		Pane root = new Pane();
		root.getChildren().addAll(rect1, rect2);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Rectangles");
		stage.show();
	}
}
