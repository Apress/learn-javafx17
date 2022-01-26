// QuadcurveTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

public class QuadcurveTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		QuadCurve qc1 = new QuadCurve(0, 100, 20, 0, 150, 100);
		qc1.setFill(Color.TRANSPARENT);
		qc1.setStroke(Color.BLACK);

		QuadCurve qc2 = new QuadCurve(0, 100, 20, 0, 150, 100);
		qc2.setFill(Color.LIGHTGRAY);   

		HBox root = new HBox(qc1, qc2);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using QuadCurves");
		stage.show();
	}
}
