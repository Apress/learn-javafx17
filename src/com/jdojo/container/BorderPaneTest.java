// BorderPaneTest.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorderPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Set the top and left child nodes to null
		Node top = null;
		Node left = null;
		
		// Build the content nodes for the center region
		VBox center = getCenter();

		// Create the right child node
		Button okBtn = new Button("Ok");
		Button cancelBtn = new Button("Cancel");

		// Make the OK and cancel buttons the same size
		okBtn.setMaxWidth(Double.MAX_VALUE); 
		VBox right = new VBox(okBtn, cancelBtn);
		right.setStyle("-fx-padding: 10;");

		// Create the bottom child node
		Label statusLbl = new Label("Status: Ready");
		HBox bottom = new HBox(statusLbl); 
		BorderPane.setMargin(bottom, new Insets(10, 0, 0, 0));
		bottom.setStyle("-fx-background-color: lavender;" +
		                "-fx-font-size: 7pt;" +
		                "-fx-padding: 10 0 0 0;" );

		BorderPane root = new BorderPane(center, top, right, bottom, left);
		root.setStyle("-fx-background-color: lightgray;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a BorderPane");
		stage.show();	
	}

	private VBox getCenter() {
		// A Label and a TextField in an HBox
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		HBox.setHgrow(nameFld, Priority.ALWAYS);
		HBox nameFields = new HBox(nameLbl, nameFld);

		// A Label and a TextArea
		Label descLbl = new Label("Description:");		
		TextArea descText = new TextArea();
		descText.setPrefColumnCount(20);
		descText.setPrefRowCount(5);
		VBox.setVgrow(descText, Priority.ALWAYS);
		
		// Box all controls in a VBox
		VBox center = new VBox(nameFields, descLbl, descText);

		return center;
	}
}
