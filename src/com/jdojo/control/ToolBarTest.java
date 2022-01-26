// ToolBarTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ToolBarTest  extends Application {
	// A canvas to draw shapes
	Canvas canvas = new Canvas(200, 200);

	public static void main(String[] args) {
		Application.launch(args);
	}


	public void start(Stage stage) {	
		// Create ToolBar items
		Button rectBtn = new Button("", new Rectangle(0, 0, 16, 16));
		Button circleBtn = new Button("", new Circle(0, 0, 8));
		Button ellipseBtn = new Button("", new Ellipse(8, 8, 8, 6));
		Button exitBtn = new Button("Exit");

		// Set tooltips
		rectBtn.setTooltip(new Tooltip("Draws a rectangle"));
		circleBtn.setTooltip(new Tooltip("Draws a circle"));
		ellipseBtn.setTooltip(new Tooltip("Draws an ellipse"));
		exitBtn.setTooltip(new Tooltip("Exits application"));

		// Add ActionEvent handlers for items
		rectBtn.setOnAction(e -> draw("Rectangle"));
		circleBtn.setOnAction(e -> draw("Circle"));
		ellipseBtn.setOnAction(e -> draw("Ellipse"));
		exitBtn.setOnAction(e -> Platform.exit());

		ToolBar toolBar = new ToolBar(rectBtn, circleBtn, ellipseBtn, 
		                              new Separator(), 
		                              exitBtn);
		BorderPane root = new BorderPane();  
		root.setTop(new VBox(new Label("Click a shape to draw."), toolBar)); 
		root.setCenter(canvas);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ToolBar Controls");
		stage.show(); 
	}
	
	public void draw(String shapeType) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 200, 200); // First clear the canvas
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
