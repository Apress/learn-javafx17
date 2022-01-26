// TranslateTest.java
package com.jdojo.transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class TranslateTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.YELLOW);
		rect2.setStroke(Color.BLACK);

		Rectangle rect3 = new Rectangle(100, 50, Color.STEELBLUE);
		rect3.setStroke(Color.BLACK);

		// Apply a translation on rect2 using the transforms sequence
		Translate translate1 = new Translate(50, 10);
		rect2.getTransforms().addAll(translate1);

		// Apply a translation on rect3 using the translateX 
		// and translateY proeprties
		rect3.setTranslateX(180);
		rect3.setTranslateY(20);

		Pane root = new Pane(rect1, rect2, rect3);
		root.setPrefSize(300, 80);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Translation Transformation");
		stage.show();
	}
}
