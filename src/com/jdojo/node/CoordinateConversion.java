// CoordinateConversion.java
package com.jdojo.node;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CoordinateConversion extends Application {

	// An instance variable to store the reference of the circle

	private Circle marker;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField salary = new TextField();

		// The Circle node is unmanaged
		marker = new Circle(5);
		marker.setManaged(false);
		marker.setFill(Color.RED);
		marker.setMouseTransparent(true);

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		hb1.getChildren().addAll(new Label("First Name:"), fName);
		hb2.getChildren().addAll(new Label("Last Name:"), lName);
		hb3.getChildren().addAll(new Label("Salary:"), salary);

		VBox root = new VBox();
		root.getChildren().addAll(hb1, hb2, hb3, marker);

		Scene scene = new Scene(root);

		// Add a focus change listener to the scene
		scene.focusOwnerProperty().addListener(
				(prop, oldNode, newNode) -> placeMarker(newNode));

		stage.setScene(scene);
		stage.setTitle("Coordinate Space Transformation");
		stage.show();
	}

	public void placeMarker(Node newNode) {
		double nodeMinX = newNode.getLayoutBounds().getMinX();
		double nodeMinY = newNode.getLayoutBounds().getMinY();
		Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMinY);
		Point2D nodeInMarkerLocal = marker.sceneToLocal(nodeInScene);
		Point2D nodeInMarkerParent = marker.localToParent(nodeInMarkerLocal);
		
		// Position the circle approperiately
		marker.relocate(nodeInMarkerParent.getX()
				+ marker.getLayoutBounds().getMinX(),
				nodeInMarkerParent.getY()
				+ marker.getLayoutBounds().getMinY());
	}
}
