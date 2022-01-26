// HBoxFillHeight.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxFillHeight extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		HBox root = new HBox(10); // 10px spacing
		
		Label descLbl = new Label("Description:");
		TextArea desc = new TextArea();
		desc.setPrefColumnCount(10);
		desc.setPrefRowCount(3);
		
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		
		// Let the Cancel button expand vertically
		cancelBtn.setMaxHeight(Double.MAX_VALUE);

		CheckBox fillHeightCbx = new CheckBox("Fill Height");
		fillHeightCbx.setSelected(true);
		
		// Add an event handler to the CheckBox, so the user can set the 
		// fillHeight property using the CheckBox
		fillHeightCbx.setOnAction(e -> 
				root.setFillHeight(fillHeightCbx.isSelected()));

		root.getChildren().addAll(
						descLbl, desc, fillHeightCbx, okBtn, cancelBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using HBox fillHeight Property");
		stage.show();
	}
}
