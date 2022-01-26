// GridPaneHVgrow.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GridPaneHVgrow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		root.setStyle("-fx-padding: 10;");
		root.setGridLinesVisible(true);

		// Add three buttons to a column
		Button b1 = new Button("One");
		Button b2 = new Button("Two");
		Button b3 = new Button("Three");
		Button b4 = new Button("Four");
		Button b5 = new Button("Five");
		Button b6 = new Button("Six");

		root.addColumn(0, b1, b2, b3);
		root.addColumn(1, b4, b5, b6);

		// Set the column constraints
		ColumnConstraints cc1 = new ColumnConstraints();
		cc1.setHgrow(Priority.NEVER);
		root.getColumnConstraints().add(cc1);
		
		// Set three different hgrow priorities for children in the second 
		// column. The highest priority, ALWAYS, will be used.
		GridPane.setHgrow(b4, Priority.ALWAYS);
		GridPane.setHgrow(b5, Priority.NEVER);
		GridPane.setHgrow(b6, Priority.SOMETIMES);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("hgrow and vgrow Constraints");
		stage.show();
	}
}
