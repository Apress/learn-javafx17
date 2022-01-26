// ImagePatternApp.java
package com.jdojo.color;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ImagePatternApp extends Application {	
	private Image img; 
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		// Create an Image object
		final String imgPath = ResourceUtil.getResourceURLStr("picture/blue_rounded_rectangle.png");
		img = new Image(imgPath);
	}

	@Override
	public void start(Stage stage) {		
		// An anchor rectangle at (0, 0) that is 25% wide and 25% tall
		// relative to the rectangle to be filled
		ImagePattern p1 = new ImagePattern(img, 0, 0, 0.25, 0.25, true);
		Rectangle r1 = new Rectangle(100, 50);
		r1.setFill(p1);
		
		// An anchor rectangle at (0, 0) that is 50% wide and 50% tall
		// relative to the rectangle to be filled
		ImagePattern p2 = new ImagePattern(img, 0, 0, 0.5, 0.5, true);
		Rectangle r2 = new Rectangle(100, 50);
		r2.setFill(p2);
		
		// Using absolute bounds for the anchor rectangle
		ImagePattern p3 = new ImagePattern(img, 40, 15, 20, 20, false);
		Rectangle r3 = new Rectangle(100, 50);
		r3.setFill(p3);
	
		// Fill a circle
		ImagePattern p4 = new ImagePattern(img, 0, 0, 0.1, 0.1, true);
		Circle c = new Circle(50, 50, 25);
		c.setFill(p4);
		
		HBox root = new HBox();
		root.getChildren().addAll(r1, r2, r3, c);
	
		Scene scene = new Scene(root);
		stage.setScene(scene);
	
		stage.setTitle("Using Image Patterns");
		stage.show();
	}
}
