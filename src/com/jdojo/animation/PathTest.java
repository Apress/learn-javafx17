// PathTest.java
package com.jdojo.animation;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create the node
		Rectangle rect = new Rectangle(20, 10, Color.RED);

		// Create the path
		Circle path = new Circle(100, 100, 100);
		path.setFill(null);
		path.setStroke(Color.BLACK);

		Group root = new Group(rect, path);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Path Transition");
		stage.show();

		// Set up a path transition for the rectangle
		PathTransition pt = new PathTransition(Duration.seconds(2), path, rect);
		pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(PathTransition.INDEFINITE);
		pt.setAutoReverse(true);
		pt.play();
	}
}
