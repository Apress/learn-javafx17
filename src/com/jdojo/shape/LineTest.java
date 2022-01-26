// LineTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LineTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// It will be just a point at (0, 0)
		Line line1 = new Line();

		Line line2 = new Line(0, 0, 50, 0);
		line2.setStrokeWidth(1.0);

		Line line3 = new Line(0, 50, 50, 0);
		line3.setStrokeWidth(2.0);
		line3.setStroke(Color.RED);

		Line line4 = new Line(0, 0, 50, 50);
		line4.setStrokeWidth(5.0);
		line4.setStroke(Color.BLUE);

		HBox root = new HBox(line1, line2, line3, line4);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Lines");
		stage.show();
	}
}
