// EffectTest.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EffectTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Text t1 = new Text("Drop Shadow!");
		t1.setFont(Font.font(24));
		t1.setEffect(new DropShadow());

		Text t2 = new Text("Blur!");
		t2.setFont(Font.font(24));
		t2.setEffect(new BoxBlur());

		Text t3 = new Text("Glow!");
		t3.setFont(Font.font(24));
		t3.setEffect(new Glow());

		Text t4 = new Text("Bloom!");
		t4.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		t4.setFill(Color.WHITE);
		t4.setEffect(new Bloom(0.10));

		// Stack the Text node with bloom effect over a Reactangle
		Rectangle rect = new Rectangle(100, 30, Color.GREEN);
		StackPane spane = new StackPane(rect, t4);

		HBox root = new HBox(t1, t2, t3, spane);
		root.setSpacing(20);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying Effects");
		stage.show();
	}
}
