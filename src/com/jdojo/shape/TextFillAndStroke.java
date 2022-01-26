// TextFillAndStroke.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextFillAndStroke extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text t1 = new Text("Stroke and fill!");
		t1.setStroke(Color.RED);
		t1.setFill(Color.WHITE);
		t1.setFont(new Font(36));

		Text t2 = new Text("Dashed Stroke!");
		t2.setStroke(Color.BLACK);
		t2.setFill(Color.WHITE);
		t2.setFont(new Font(36));
		t2.getStrokeDashArray().addAll(5.0, 5.0);

		HBox root = new HBox(t1, t2); 
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Stroke and Fill for Text Nodes");
		stage.show();
	}
}
