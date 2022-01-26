// CaptureBubblingOrder.java
package com.jdojo.event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class CaptureBubblingOrder extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle (50, 50, 50);
		circle.setFill(Color.CORAL);
		
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(Color.TAN);

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(circle, rect);
		
		Scene scene = new Scene(root);
		
		// Create two EventHandlders
		EventHandler<MouseEvent> filter = e -> handleEvent("Capture", e);
		EventHandler<MouseEvent> handler = e -> handleEvent("Bubbling", e);

		// Register filters
		stage.addEventFilter(MOUSE_CLICKED, filter);
		scene.addEventFilter(MOUSE_CLICKED, filter);
		root.addEventFilter(MOUSE_CLICKED, filter);
		circle.addEventFilter(MOUSE_CLICKED, filter);

		// Register handlers
		stage.addEventHandler(MOUSE_CLICKED, handler);
		scene.addEventHandler(MOUSE_CLICKED, handler);
		root.addEventHandler(MOUSE_CLICKED, handler);
		circle.addEventHandler(MOUSE_CLICKED, handler);

		stage.setScene(scene);
		stage.setTitle("Event Capture and Bubbling Execution Order");
		stage.show();
	}
	
	public void handleEvent(String phase, MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		
		// Get coordinates of the mouse cursor relative to the event source
		double x = e.getX();
		double y = e.getY();

		System.out.println(phase + ": Type=" + type + 
		                   ", Target=" + target + ", Source=" +  source + 
		                   ", location(" + x + ", " + y + ")");
	}
}
