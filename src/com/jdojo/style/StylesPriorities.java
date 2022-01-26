// StylesPriorities.java
package com.jdojo.style;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StylesPriorities extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button yesBtn = new Button("Yes");
		Button noBtn = new Button("No");
		Button cancelBtn = new Button("Cancel");

		// Change the font size for the Yes button 
		// using two methods: inline style and JavaFX API
		yesBtn.setStyle("-fx-font-size: 16px"); 
		yesBtn.setFont(new Font(10));

		// Change the font size for the No button using the JavaFX API
		noBtn.setFont(new Font(8));

		HBox root = new HBox();
		root.setSpacing(10);
		root.getChildren().addAll(yesBtn, noBtn, cancelBtn);

		Scene scene = new Scene(root);

		// Add a style sheet to the scene		
		var url = ResourceUtil.getResourceURLStr("css/stylespriorities.css");
		scene.getStylesheets().addAll(url);

		stage.setScene(scene);
		stage.setTitle("Styles Priorities");
		stage.show();
	}
}
