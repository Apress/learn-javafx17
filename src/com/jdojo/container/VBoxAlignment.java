// VBoxAlignment.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		VBox vbox = new VBox(10);
		vbox.setPrefSize(200, 100);
		vbox.getChildren().addAll(okBtn, cancelBtn);

		// Set the alignment to bottom right
		vbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		vbox.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Using VBox Alignment Property");
		stage.show();		
	}
}
