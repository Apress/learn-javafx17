// ScrollPaneTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScrollPaneTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Label poemLbl = new Label("I told her this; her laughter light\n" + 
		                         "Is ringing in my ears;\n" + 
		                         "And when I think upon that night\n" + 
		                         "My eyes are dim with tears.");

		// Create a scroll pane with poemLbl as its content
		ScrollPane sPane = new ScrollPane(poemLbl);
		sPane.setPannable(true);

		HBox root = new HBox(sPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ScrollPane Controls");
		stage.show();
	}
}
