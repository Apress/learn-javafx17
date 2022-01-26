// RotatePropertyTest.java
package com.jdojo.transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RotatePropertyTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setStroke(Color.BLACK);
		rect2.setOpacity(0.5);

		// Use the rotate proeprty of the node class
		rect2.setRotate(30);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(300, 80);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Rotation Transformation");
		stage.show();
	}
}
