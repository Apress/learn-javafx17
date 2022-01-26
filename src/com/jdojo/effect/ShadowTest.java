// ShadowTest.java
package com.jdojo.effect;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShadowTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Shadow of a Text node
		Text t1 = new Text("Shadow");
		t1.setFont(Font.font(36));
		t1.setEffect(new Shadow());

		// Create a Glow effect using a Shadow
		Text t2Original = new Text("Glow");
		t2Original.setFont(Font.font(36));
		Text t2 = new Text("Glow");
		t2.setFont(Font.font(36));
		Shadow s2 = new Shadow();
		s2.setColor(Color.YELLOW);
		t2.setEffect(s2);		
		StackPane glow = new StackPane(t2Original, t2);

		// Create a DropShadow effect using a Shadow 
		Text t3Original = new Text("DropShadow");
		t3Original.setFont(Font.font(36));
		Text t3 = new Text("DropShadow");
		t3.setFont(Font.font(36));
		Shadow s3 = new Shadow();
		t3.setEffect(s3);
		StackPane dropShadow = new StackPane(t3, t3Original);

		HBox root = new HBox(t1, glow, dropShadow);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Shadow Effect");
		stage.show();
	}
}
