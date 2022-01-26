// TooltipTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TooltipTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		Button saveBtn = new Button("Save");
		Button closeBtn = new Button("Close");

		// Set an ActionEvent handler
		closeBtn.setOnAction(e -> stage.close());

		// Add tooltips for Name field and Save button
		nameFld.setTooltip(new Tooltip("Enter your name\n(Max. 10 chars)"));
		saveBtn.setTooltip(new Tooltip("Saves the data"));
	
		// Create and configure the Tooltip for Close button
		Tooltip closeBtnTip = new Tooltip("Closes the window");
		closeBtnTip.setStyle("-fx-background-color: yellow; " + 
		                     " -fx-text-fill: black;");

		// Display the icon above the text
		closeBtnTip.setContentDisplay(ContentDisplay.TOP);

		Label closeTipIcon = new Label("X");
		closeTipIcon.setStyle("-fx-text-fill: red;");
		closeBtnTip.setGraphic(closeTipIcon);

		// Set its Tooltip for Close button
		closeBtn.setTooltip(closeBtnTip);

		HBox root = new HBox(nameLbl, nameFld, saveBtn, closeBtn);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Tooltip Controls");
		stage.show();
	}
}
