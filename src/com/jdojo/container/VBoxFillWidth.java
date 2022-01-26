// VBoxFillWidth.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxFillWidth extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Button b1 = new Button("New");
		Button b2 = new Button("New Modified");
		Button b3 = new Button("Not Modified");
		Button b4 = new Button("Data Modified");
		
		// Set the max width of the buttons to Double.MAX_VALUE,
		// so they can grow horizontally
		b1.setMaxWidth(Double.MAX_VALUE);
		b2.setMaxWidth(Double.MAX_VALUE);
		b3.setMaxWidth(Double.MAX_VALUE);
	 	b4.setMaxWidth(Double.MAX_VALUE);
		
		VBox root = new VBox(10, b1, b2, b3, b4);		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using VBox fillWidth Property");
		stage.show();
	}
}
