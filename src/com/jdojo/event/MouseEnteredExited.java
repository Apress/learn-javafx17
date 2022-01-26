// MouseEnteredExited.java
package com.jdojo.event;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED; 
import static javafx.scene.input.MouseEvent.MOUSE_EXITED; 

public class MouseEnteredExited  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Circle circle = new Circle (50, 50, 50);
		circle.setFill(Color.GRAY);

		HBox root = new HBox();		
		root.setPadding(new Insets(20));
		root.setSpacing(20);		
		root.getChildren().addAll(circle);

		// Create a mouse event handler
		EventHandler<MouseEvent> handler = e -> handle(e);  

		// Add mouse-entered and mouse-exited event handlers to the HBox
		root.addEventHandler(MOUSE_ENTERED, handler);
		root.addEventHandler(MOUSE_EXITED, handler);

		// Add mouse-entered and mouse-exited event handlers to the Circle
		circle.addEventHandler(MOUSE_ENTERED, handler);
		circle.addEventHandler(MOUSE_EXITED, handler);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Mouse Entered and Exited Events");
		stage.show();
	}
	
	public void handle(MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		System.out.println("Type=" + type + ", Target=" + target + ", Source=" +  source);
	}
}
