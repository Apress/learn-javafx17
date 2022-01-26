// RotateTest.java
package com.jdojo.animation;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RotateTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(50, 50, Color.RED);
		HBox.setMargin(rect, new Insets(20));
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Rotate Transition");
		stage.show();

		// Set up a rotate transition the rectangle
		RotateTransition rt = new RotateTransition(Duration.seconds(2), rect);
		rt.setFromAngle(0.0);
		rt.setToAngle(360.0);
		rt.setCycleCount(RotateTransition.INDEFINITE);
		rt.setAutoReverse(true);
		rt.play();
	}
}
