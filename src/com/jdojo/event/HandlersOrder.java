// HandlersOrder.java
package com.jdojo.event;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HandlersOrder extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle(50, 50, 50);
		circle.setFill(Color.CORAL);

		HBox root = new HBox();
		root.getChildren().addAll(circle);
		Scene scene = new Scene(root);

		/* Register three handlers for the circle that can handle mouse-clicked events */
		// This will be called last
		circle.addEventHandler(MouseEvent.ANY, e -> handleAnyMouseEvent(e));

		// This will be called first
		circle.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> handleMouseClicked("addEventHandler()", e));

		// This will be called second
		circle.setOnMouseClicked(e -> handleMouseClicked("setOnMouseClicked()", e));

		stage.setScene(scene);
		stage.setTitle("Execution Order of Event Handlers of a Node");
		stage.show();
	}

	public void handleMouseClicked(String registrationMethod, MouseEvent e) {
		System.out.println(registrationMethod
				+ ": MOUSE_CLICKED handler detected a mouse click.");
	}

	public void handleAnyMouseEvent(MouseEvent e) {
		// Print a message only for mouse-clicked events, ignoring
		// other mouse events such as mouse-pressed, mouse-released, etc.
		if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("MouseEvent.ANY handler detected a mouse click.");
		}
	}
}
