// SimplePressDragRelease.java
package com.jdojo.dnd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimplePressDragRelease extends Application {
	TextField sourceFld = new TextField("Source Node");
	TextField targetFld = new TextField("Target node");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build the UI
		GridPane root = getUI();

		// Add event handlers
		this.addEventHanders();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A simple press-drag-release gesture");
		stage.show();
	}

	private GridPane getUI() {
		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(20);
		pane.addRow(0, new Label("Source Node:"), sourceFld);
		pane.addRow(1, new Label("Target Node:"), targetFld);
		return pane;
	}

	private void addEventHanders() {
		// Add mouse event handlers for the source
		sourceFld.setOnMousePressed(e -> print("Source: pressed"));
		sourceFld.setOnMouseDragged(e -> print("Source: dragged"));
		sourceFld.setOnDragDetected(e -> print("Source: dragged detected"));
		sourceFld.setOnMouseReleased(e -> print("Source: released"));

		// Add mouse event handlers for the target
		targetFld.setOnMouseDragEntered(e -> print("Target: drag entered"));
		targetFld.setOnMouseDragOver(e -> print("Target: drag over"));
		targetFld.setOnMouseDragReleased(e -> print("Target: drag released"));
		targetFld.setOnMouseDragExited(e -> print("Target: drag exited"));
	}

	private void print(String msg) {
		System.out.println(msg);
	}
}
