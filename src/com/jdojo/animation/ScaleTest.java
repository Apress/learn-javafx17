// ScaleTest.java
package com.jdojo.animation;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScaleTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Scale Transition");
		stage.show();

		// Set up a scale transition for the rectangle
		ScaleTransition st = new ScaleTransition(Duration.seconds(2), rect);
		st.setFromX(1.0);
		st.setToX(0.20);
		st.setFromY(1.0);
		st.setToY(0.20);
		st.setCycleCount(ScaleTransition.INDEFINITE);
		st.setAutoReverse(true);
		st.play();
	}
}
