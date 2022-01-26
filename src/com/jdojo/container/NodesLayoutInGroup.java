// NodesLayoutInGroup.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NodesLayoutInGroup extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create two buttons
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		// Set the location of the OK button
		okBtn.setLayoutX(10);
		okBtn.setLayoutY(10);

		// Set the location of the Cancel botton relative to the OK button 		
		NumberBinding layoutXBinding = 
			okBtn.layoutXProperty().add(okBtn.widthProperty().add(10));
		cancelBtn.layoutXProperty().bind(layoutXBinding);
		cancelBtn.layoutYProperty().bind(okBtn.layoutYProperty());

		Group root = new Group();		
		root.getChildren().addAll(okBtn, cancelBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Positioning Nodes in a Group");
		stage.show();
	}
}
