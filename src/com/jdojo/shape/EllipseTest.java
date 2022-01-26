// EllipseTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class EllipseTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {	
		Ellipse e1 = new Ellipse(50, 30);
		e1.setFill(Color.LIGHTGRAY);

		Ellipse e2 = new Ellipse(60, 30);
		e2.setFill(Color.YELLOW);
		e2.setStroke(Color.BLACK);
		e2.setStrokeWidth(2.0);

		// Draw a circle using the Ellipse class (radiusX=radiusY=30)
		Ellipse e3 = new Ellipse(30, 30);
		e3.setFill(Color.YELLOW);
		e3.setStroke(Color.BLACK);
		e3.setStrokeWidth(2.0);

		HBox root = new HBox(e1, e2, e3);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Ellipses");
		stage.show();
	}
}
