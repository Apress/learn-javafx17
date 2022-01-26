// FillTest.java
package com.jdojo.animation;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FillTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fill Transition");
		stage.show();

		// Set up a fill transition for the rectangle
		FillTransition fillTransition = new FillTransition(Duration.seconds(2), rect);
		fillTransition.setFromValue(Color.BLUEVIOLET);
		fillTransition.setToValue(Color.AZURE);
		fillTransition.setCycleCount(FillTransition.INDEFINITE);
		fillTransition.setAutoReverse(true);
		fillTransition.play();
	}
}
