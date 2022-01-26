// DepthTestCheck.java
package com.jdojo.shape3d;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.DepthTest;
import javafx.stage.Stage;

public class DepthTestCheck  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create two rectangles and add then to a Group
		Rectangle red = new Rectangle(100, 100);
		red.setFill(Color.RED);
		red.setTranslateX(100);
		red.setTranslateY(100);
		red.setTranslateZ(400);

		Rectangle green = new Rectangle(100, 100);
		green.setFill(Color.GREEN);
		green.setTranslateX(150);
		green.setTranslateY(150);
		green.setTranslateZ(300);

		Group center = new Group(green, red);

		CheckBox depthTestCbx = new CheckBox("DepthTest for Rectangles");
		depthTestCbx.setSelected(true);	
		depthTestCbx.selectedProperty().addListener(
					(prop, oldValue, newValue) -> {
			if (newValue) {
				red.setDepthTest(DepthTest.ENABLE);
				green.setDepthTest(DepthTest.ENABLE);
			}
			else {
				red.setDepthTest(DepthTest.DISABLE);
				green.setDepthTest(DepthTest.DISABLE);
			}
		});

		// Create a BorderPane as the root node for the scene. Need to 
		// set the background transparent, so the cemera can view the
		// rectangles behind the surface of the BorderPane
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: transparent;");	
		root.setTop(depthTestCbx);
		root.setCenter(center);

		// Craete a scene with depthBuffer enabled
		Scene scene = new Scene(root, 200, 200, true);

		// Need to set a camera to look into the 3D space of the scene
		scene.setCamera(new PerspectiveCamera());

		stage.setScene(scene);
		stage.setTitle("Depth Test");
		stage.show();
	}
}
