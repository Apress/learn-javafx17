// ScalePivotPointTest.java
package com.jdojo.transform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ScalePivotPointTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setX(150);
		rect1.setStroke(Color.BLACK);
		rect1.setOpacity(0.5);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setX(150);
		rect2.setStroke(Color.BLACK);

		// Apply a scale on rect2. The origin of the local coordinate system
		// of rect4 is the pivot point
		Scale scale = new Scale(0.5, 0.5);
		rect2.getTransforms().addAll(scale);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(300, 60);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Scale Transformation");
		stage.show();
	}
}
