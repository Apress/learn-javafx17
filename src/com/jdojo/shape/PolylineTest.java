// PolylineTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class PolylineTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Polyline triangle1 = new Polyline();
		triangle1.getPoints().addAll(50.0, 0.0,
		                             0.0, 50.0, 
		                             100.0, 50.0, 
		                             50.0, 0.0);
		triangle1.setFill(Color.WHITE);
		triangle1.setStroke(Color.RED);

		// Create an open parallelogram
		Polyline parallelogram = new Polyline();
		parallelogram.getPoints().addAll(30.0, 0.0,
		                                 130.0, 0.0, 
		                                 100.00, 50.0, 
		                                 0.0, 50.0);
		parallelogram.setFill(Color.YELLOW);
		parallelogram.setStroke(Color.BLACK);

		Polyline hexagon = new Polyline(100.0, 0.0, 
		                                120.0, 20.0, 
		                                120.0, 40.0, 
		                                100.0, 60.0, 
		                                80.0, 40.0, 
		                                80.0, 20.0,
		                                100.0, 0.0);
		hexagon.setFill(Color.WHITE);
		hexagon.setStroke(Color.BLACK);

		HBox root = new HBox(triangle1, parallelogram, hexagon);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Polylines");
		stage.show();
	}
}
