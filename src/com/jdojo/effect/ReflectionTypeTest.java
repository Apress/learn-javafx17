// ReflectionTypeTest.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

public class ReflectionTypeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text text = new Text();
		text.setText("Chatar");
		text.setFill(Color.RED);
		text.setFont(Font.font("null", FontWeight.BOLD, 72));
		text.setBoundsType(TextBoundsType.VISUAL);

		Rectangle rect = new Rectangle(300, 100);
		rect.setFill(Color.LIGHTGRAY);

		// Set the same Lighting effect to both Rectangle and Text nodes
		Lighting effect = new Lighting();
		text.setEffect(effect);
		rect.setEffect(effect);

		StackPane sp = new StackPane(rect, text);

		GridPane controllsrPane = LightingUtil.getPropertyControllers(effect);
		BorderPane root = new BorderPane();
		root.setCenter(sp);
		root.setRight(controllsrPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Controlling Reflection Details");
		stage.show();
	}
}
