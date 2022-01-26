// HelloFXAppWithAScene.java
package com.jdojo.intro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloFXAppWithAScene extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
 
	@Override
	public void start(Stage stage) {
		Text msg = new Text("Hello JavaFX");
		VBox root = new VBox();
		root.getChildren().add(msg);

		Scene scene = new Scene(root, 300, 50);
		stage.setScene(scene);
		stage.setTitle("Hello JavaFX Application with a Scene");
		stage.show();
	}
}
