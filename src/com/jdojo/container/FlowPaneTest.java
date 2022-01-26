// FlowPaneTest.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		double hgap = 5;
		double vgap = 10;
		FlowPane root = new FlowPane(hgap, vgap);

		// Add ten buttons to the flow pane
		for(int i = 1; i <= 10; i++) {
			root.getChildren().add(new Button("Button " + i));
		}
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Horizontal FlowPane");
		stage.show();
	}
}
