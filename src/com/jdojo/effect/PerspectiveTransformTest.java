// PerspectiveTransformTest.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PerspectiveTransformTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create the efefct and set the mapping for the corners
		PerspectiveTransform effect = new PerspectiveTransform();
		effect.setUlx(0.0);
		effect.setUly(0.0);
		effect.setUrx(250.0);
		effect.setUry(20.0);
		effect.setLrx(310.0);
		effect.setLry(60.0);
		effect.setLlx(20.0);
		effect.setLly(60.0);

		// Create two rectangles and two Text nodes. Apply effects 
		// to one set and show another set without effect
		Rectangle rect1 = new Rectangle(200, 60, Color.LIGHTGRAY);
		Rectangle rect2 = new Rectangle(200, 60, Color.LIGHTGRAY);

		Text text1 = new Text();
		text1.setX(20);
		text1.setY(40);
		text1.setText("Welcome");
		text1.setFill(Color.RED);
		text1.setFont(Font.font(null, FontWeight.BOLD, 36));		
		
		System.out.println(text1.getLayoutBounds());
		
		Text text2 = new Text();
		text2.setX(20);
		text2.setY(40);
		text2.setText("Welcome");
		text2.setFill(Color.RED);
		text2.setFont(Font.font(null, FontWeight.BOLD, 36));

		// Group the original nodes
		Group group1 = new Group(rect1, text1);	
	    
		// Group the nodes with the effect
		Group group2 = new Group(rect2, text2);		
		group2.setEffect(effect);
		group2.setCache(true); // A hint to cache the bitmap for the group

		HBox root = new HBox(group1, group2);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root, 600, 100);
		stage.setScene(scene);
		stage.setTitle("Applying the PerspectiveTransform Effect");		
		stage.show();		
	}	
}
