// NodeSizes.java
package com.jdojo.node;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NodeSizes extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button btn = new Button("Hello JavaFX!");

		HBox root = new HBox();
		root.getChildren().addAll(btn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sizes of a Node");
		stage.show();

		// Print button's sizes
		System.out.println("Before changing button properties:");
		printSizes(btn);

		// Change button's properties
		btn.setWrapText(true);
		btn.setPrefWidth(80);
		stage.sizeToScene();

		// Print button's sizes
		System.out.println("\nAfter changing button properties:");
		printSizes(btn);

	}

	public void printSizes(Button btn) {
		System.out.println("btn.getContentBias() = " + btn.getContentBias());

		System.out.println("btn.getPrefWidth() = " + btn.getPrefWidth() + 
		                   ", btn.getPrefHeight() = " + btn.getPrefHeight());

		System.out.println("btn.getMinWidth() = " + btn.getMinWidth() + 
		                   ", btn.getMinHeight() = " + btn.getMinHeight());

		System.out.println("btn.getMaxWidth() = " + btn.getMaxWidth() + 
		                   ", btn.getMaxHeight() = " + btn.getMaxHeight());

		double prefWidth = btn.prefWidth(-1);
		System.out.println("btn.prefWidth(-1) = " + prefWidth + 
		       ", btn.prefHeight(prefWidth) = " + btn.prefHeight(prefWidth));

		double minWidth = btn.minWidth(-1);
		System.out.println("btn.minWidth(-1) = " + minWidth + 
		       ", btn.minHeight(minWidth) = " + btn.minHeight(minWidth));

		double maxWidth = btn.maxWidth(-1);
		System.out.println("btn.maxWidth(-1) = " + maxWidth + 
		       ", btn.maxHeight(maxWidth) = " + btn.maxHeight(maxWidth));

		System.out.println("btn.getWidth() = " + btn.getWidth() + 
		                   ", btn.getHeight() = " + btn.getHeight());
	}
}
