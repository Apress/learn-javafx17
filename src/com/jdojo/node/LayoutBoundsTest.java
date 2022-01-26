// LayoutBoundsTest.java
package com.jdojo.node;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutBoundsTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("Close");
		b1.setEffect(new DropShadow());

		Button b2 = new Button("Close");

		Button b3 = new Button("Close");
		b3.setEffect(new DropShadow());
		b3.setRotate(30);

		Button b4 = new Button("Close");

		VBox root = new VBox();
		root.getChildren().addAll(b1, b2, b3, b4);		

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Testing LayoutBounds");
		stage.show();

		System.out.println("b1=" + b1.getLayoutBounds());
		System.out.println("b2=" + b2.getLayoutBounds());
		System.out.println("b3=" + b3.getLayoutBounds());
		System.out.println("b4=" + b4.getLayoutBounds());
	}
}
