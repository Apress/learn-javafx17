// FadeTest.java
package com.jdojo.animation;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fade-in and Fade-out");
		stage.show();

		// Set up a fade-in and fade-out animation for the rectangle
		FadeTransition fadeInOut = new FadeTransition(Duration.seconds(2), rect);
		fadeInOut.setFromValue(1.0);
		fadeInOut.setToValue(.20);
		fadeInOut.setCycleCount(FadeTransition.INDEFINITE);
		fadeInOut.setAutoReverse(true);
		fadeInOut.play();
	}
}
