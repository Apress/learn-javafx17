// ContextMenuTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ContextMenuTest extends Application {
	// A canvas to draw shapes
	Canvas canvas = new Canvas(200, 200);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add mouse click event handler to the canvas to show the context menu
		canvas.setOnMouseClicked(e -> showContextMenu(e));

		BorderPane root = new BorderPane();  
		root.setTop(new Label("Right click below to display a context menu."));
		root.setCenter(canvas);        
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Context Menus");
		stage.show();
	}

	public void showContextMenu(MouseEvent me) {
		// Show menu only on right click
		if (me.getButton() == MouseButton.SECONDARY) { 
			MenuItem rectItem = new MenuItem("Rectangle");
			MenuItem circleItem = new MenuItem("Circle");
			MenuItem ellipseItem = new MenuItem("Ellipse"); 
			rectItem.setOnAction(e -> draw("Rectangle"));
			circleItem.setOnAction(e -> draw("Circle"));
			ellipseItem.setOnAction(e -> draw("Ellipse")); 
			ContextMenu ctxMenu = 
					new ContextMenu(rectItem, circleItem, ellipseItem);
			ctxMenu.show(canvas, me.getScreenX(), me.getScreenY());
		}
	}

	public void draw(String shapeType) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 200, 200); // clear the canvas first
		gc.setFill(Color.TAN);

		if (shapeType.equals("Rectangle")) {
			gc.fillRect(0, 0, 200, 200);
		} else if (shapeType.equals("Circle")) { 
			gc.fillOval(0, 0, 200, 200); 
		} else if (shapeType.equals("Ellipse")) {
			gc.fillOval(10, 40, 180, 120);
		}
	}
}
