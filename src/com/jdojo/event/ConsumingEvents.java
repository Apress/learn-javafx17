// ConsumingEvents.java
package com.jdojo.event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ConsumingEvents extends Application {	
	private CheckBox consumeEventCbx = new CheckBox("Consume Mouse Click at Circle");
	
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
		root.getChildren().addAll(circle, rect, consumeEventCbx);

		Scene scene = new Scene(root);		

		// Register mouse-clicked event handlers to all nodes, 
		// except the rectangle and checkbox	
		EventHandler<MouseEvent> handler = e -> handleEvent(e);
		EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e);

		stage.addEventHandler(MOUSE_CLICKED, handler);
		scene.addEventHandler(MOUSE_CLICKED, handler);
		root.addEventHandler(MOUSE_CLICKED, handler);
		circle.addEventHandler(MOUSE_CLICKED, circleMeHandler);

		stage.setScene(scene);
		stage.setTitle("Consuming Events");
		stage.show();
	}
	
	public void handleEvent(MouseEvent e) {
		print(e);	
	}
	
	public void handleEventforCircle(MouseEvent e) {
		print(e);
		if (consumeEventCbx.isSelected()) {
			e.consume();
		}
	}

	public void print(MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		
		// Get coordinates of the mouse cursor relative to the event source
		double x = e.getX();
		double y = e.getY();

		System.out.println("Type=" + type + ", Target=" + target + 
		                   ", Source=" +  source + 
		                   ", location(" + x + ", " + y + ")");
	}
}
