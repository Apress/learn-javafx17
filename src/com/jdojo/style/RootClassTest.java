// RootClassTest.java
package com.jdojo.style;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RootClassTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameTf = new TextField("");
		Button closeBtn = new Button("Close");

		HBox root = new HBox();
		root.getChildren().addAll(nameLbl, nameTf, closeBtn);

		Scene scene = new Scene(root); 	
		/* The root variable is assigned a default style class name "root" */

		var url = ResourceUtil.getResourceURLStr("css/rootclass.css");
		scene.getStylesheets().add(url);

		stage.setScene(scene);
		stage.setTitle("Using the root Style Class Selector");
		stage.show();
	}
}
