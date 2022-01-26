// ToggleButtonTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToggleButtonTest extends Application {
	Label userSelectionMsg = new Label("Your selection: None");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create four ToggleButtons
		ToggleButton springBtn = new ToggleButton("Spring");
		ToggleButton summerBtn = new ToggleButton("Summer");
		ToggleButton fallBtn = new ToggleButton("Fall");
		ToggleButton winterBtn = new ToggleButton("Winter");

		// Add all ToggleButtons to a ToggleGroup
		ToggleGroup group = new ToggleGroup();		
		group.getToggles().addAll(springBtn, summerBtn, fallBtn, winterBtn);

		// Track the selection changes and display the currently selected season
		group.selectedToggleProperty().addListener(this::changed);

		Label msg = new Label("Select the season you like:");

		// Add ToggleButtons to an HBox
		HBox buttonBox = new HBox(springBtn, summerBtn, fallBtn, winterBtn);
		buttonBox.setSpacing(10);

		VBox root = new VBox(userSelectionMsg, msg, buttonBox);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ToggleButtons in a Group");
		stage.show();
	}
	
	// A change listener to track the selection in the group
	public void changed(ObservableValue<? extends Toggle> observable, 
	                    Toggle oldBtn, 
	                    Toggle newBtn) {
		String selectedLabel = "None";
		if (newBtn != null ) {
			selectedLabel = ((Labeled)newBtn).getText();
		}

		userSelectionMsg.setText("Your selection: " + selectedLabel);
	}
}
