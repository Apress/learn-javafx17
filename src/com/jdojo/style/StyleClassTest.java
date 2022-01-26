// StyleClassTest.java
package com.jdojo.style;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StyleClassTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameTf = new TextField("");
		Button closeBtn  = new Button("Close");
		closeBtn.setOnAction(e -> Platform.exit());
			
		HBox root = new HBox();		
		root.getChildren().addAll(nameLbl, nameTf, closeBtn);
		
		// Set the styleClass for the HBox to "hbox"
		root.getStyleClass().add("hbox");

		Scene scene = new Scene(root);
		// TODO: book text
		var url = ResourceUtil.getResourceURLStr("css/styleclass.css");
		scene.getStylesheets().add(url);

		stage.setScene(scene);
		stage.setTitle("Using Style Class Selectors");
		stage.show();
	}
}
