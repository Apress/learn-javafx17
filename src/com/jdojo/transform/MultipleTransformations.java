// MultipleTransformations.java
package com.jdojo.transform;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MultipleTransformations extends Application {
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

		Rectangle rect3 = new Rectangle(100, 50, Color.LIGHTCYAN);
		rect3.setStroke(Color.BLACK);
		rect3.setOpacity(0.5);

		// apply transformations to rect2
		rect2.setTranslateX(100);
		rect2.setTranslateY(0);
		rect2.setRotate(30);
		rect2.setScaleX(1.2);
		rect2.setScaleY(1.2);

		// Apply the same transformation as on rect2, but in a different order
		rect3.getTransforms().addAll(new Scale(1.2, 1.2, 50, 25),  
		                             new Rotate(30, 50, 25),
		                             new Translate(100, 0));

		Group root = new Group(rect1, rect2, rect3);		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying Multiple Transformations");
		stage.show();
	}
}
