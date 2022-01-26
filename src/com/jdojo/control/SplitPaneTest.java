// SplitPaneTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) { 
		TextArea desc1 = new TextArea();
		desc1.setPrefColumnCount(10);
		desc1.setPrefRowCount(4);

		TextArea desc2 = new TextArea();
		desc2.setPrefColumnCount(10);
		desc2.setPrefRowCount(4);

		VBox vb1 = new VBox(new Label("Description1"), desc1);
		VBox vb2 = new VBox(new Label("Description2"), desc2); 

		SplitPane sp = new SplitPane();
		sp.getItems().addAll(vb1, vb2);

		HBox root = new HBox(sp);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);      
		stage.setTitle("Using SplitPane Controls");
		stage.show();
	}
}
