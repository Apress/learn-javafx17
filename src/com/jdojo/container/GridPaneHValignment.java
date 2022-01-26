// GridPaneHValignment.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GridPaneHValignment extends Application {
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
		root.addColumn(0, b1, b2, b3);

		// Set the column constraints
		ColumnConstraints cc1 = new ColumnConstraints(100);
		cc1.setHalignment(HPos.RIGHT);
		root.getColumnConstraints().add(cc1);

		// Set the row constraints
		RowConstraints rc1 = new RowConstraints(40);
		rc1.setValignment(VPos.TOP);

		RowConstraints rc2 = new RowConstraints(40);
		rc2.setValignment(VPos.TOP);

		RowConstraints rc3 = new RowConstraints(40);
		root.getRowConstraints().addAll(rc1, rc2, rc3);

		// Override the halignment for b2 set in the column
		GridPane.setHalignment(b2, HPos.CENTER);

		// Override the valignment for b1 set in the row
		GridPane.setValignment(b1, VPos.BOTTOM);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("halignemnt and valignment Constraints");
		stage.show();
	}
}
