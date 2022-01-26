// TextTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
 
	@Override
	public void start(Stage stage) {
		Text t1 = new Text("Hello Text Node!");
		
		Text t2 = new Text("Bold and Big");
		t2.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
		
		Text t3 = new Text("Reflection");
		t3.setEffect(new Reflection());
		t3.setStroke(Color.BLACK);
		t3.setFill(Color.WHITE);
		t3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		HBox root = new HBox(t1, t2, t3); 
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Text Nodes");
		stage.show();
	}
}
