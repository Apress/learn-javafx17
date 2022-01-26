// StageStyleApp.java
package com.jdojo.stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.stage.StageStyle.DECORATED;
import static javafx.stage.StageStyle.UNDECORATED;
import static javafx.stage.StageStyle.TRANSPARENT;
import static javafx.stage.StageStyle.UNIFIED;
import static javafx.stage.StageStyle.UTILITY;

public class StageStyleApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// A label to display the style type
		Label styleLabel = new Label("Stage Style");
		
		// A button to close the stage
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> stage.close()); 

		VBox root = new VBox(); 
		root.getChildren().addAll(styleLabel, closeButton);
		Scene scene = new Scene(root, 100, 70);
		stage.setScene(scene);
	
		// The title of the stage is not visible for all styles.
		stage.setTitle("The Style of a Stage");
		
		/* Uncomment one of the following statements at a time */
		this.show(stage, styleLabel, DECORATED);
		//this.show(stage, styleLabel, UNDECORATED);
		//this.show(stage, styleLabel, TRANSPARENT);
		//this.show(stage, styleLabel, UNIFIED);
		//this.show(stage, styleLabel, UTILITY);
	}
	
	private void show(Stage stage, Label styleLabel, StageStyle style) {
		// Set the text for the label to match the style
		styleLabel.setText(style.toString());
		
		// Set the style
		stage.initStyle(style);
		
		// For a transparent style, set the scene fill to null. Otherwise, the 
		// content area will have the default white background of the scene.
		if (style == TRANSPARENT) {
			stage.getScene().setFill(null);
			stage.getScene().getRoot().setStyle(
				"-fx-background-color: transparent");
		} else if(style == UNIFIED) {
			stage.getScene().setFill(Color.TRANSPARENT);
		}

		// Show the stage
		stage.show();
	}
}
