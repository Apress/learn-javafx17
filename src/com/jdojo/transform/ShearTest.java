// ShearTest.java
package com.jdojo.transform;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class ShearTest extends Application {
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

		// Apply a shear on rect2. The x and y multipliers are 0.5 and
		// (0, 0) is the pivot point.
		Shear shear = new Shear(0.5, 0.5);
		rect2.getTransforms().addAll(shear);

		Group root = new Group(rect1, rect2);		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Shear Transformation");
		stage.show();
	}
}
