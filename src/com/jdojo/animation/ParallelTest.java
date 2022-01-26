// ParallelTest.java
package com.jdojo.animation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ParallelTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(100, 100, Color.RED);
		HBox.setMargin(rect, new Insets(20));

		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Parallel Transition");
		stage.show();

		// Set up a fade transition
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
		fadeTransition.setFromValue(0.20);
		fadeTransition.setToValue(1.0);
		fadeTransition.setCycleCount(2);
		fadeTransition.setAutoReverse(true);

		// Set up a rotate transition
		RotateTransition rotateTransition = 
			new RotateTransition(Duration.seconds(2));
		rotateTransition.setFromAngle(0.0);
		rotateTransition.setToAngle(360.0);
		rotateTransition.setCycleCount(2);
		rotateTransition.setAutoReverse(true);

		// Create and start a sequential transition
		ParallelTransition pt = new ParallelTransition();

		// Rectangle is the node for all animations
		pt.setNode(rect); 
		pt.getChildren().addAll(fadeTransition, rotateTransition);
		pt.setCycleCount(PathTransition.INDEFINITE);
		pt.play();
	}
}
