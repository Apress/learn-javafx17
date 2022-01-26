// GridPaneHgapVgap.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneHgapVgap extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label fnameLbl = new Label("First Name:");
		TextField fnameFld = new TextField();
		Label lnameLbl = new Label("Last Name:");
		TextField lnameFld = new TextField();
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		
		// The Ok button should fill its cell
		okBtn.setMaxWidth(Double.MAX_VALUE);

		// Create a GridPane and set its background color to lightgray
		GridPane root = new GridPane();
		root.setGridLinesVisible(true); // Make grid lines visible
		root.setHgap(10); // hgap = 10px
		root.setVgap(5);  // vgap = 5px
		root.setStyle("-fx-padding: 10;-fx-background-color: lightgray;");

		// Add children to the GridPane
		root.addRow(0, fnameLbl, fnameFld, okBtn);
		root.addRow(1, lnameLbl, lnameFld, cancelBtn);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using hgap and vgap Properties for a GridPane");
		stage.show();
	}
}
