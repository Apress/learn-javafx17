// TranslateTest.java
package com.jdojo.animation;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TranslateTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text msg = new Text("JavaFX animation is cool!");
		msg.setTextOrigin(VPos.TOP);
		msg.setFont(Font.font(24));

		Pane root = new Pane(msg);
		root.setPrefSize(500, 70);
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Scrolling Text using a Translate Transition");
		stage.show();

		// Set up a translate transition for the Text object
		TranslateTransition tt = new TranslateTransition(Duration.seconds(2), msg);
		tt.setFromX(scene.getWidth());
		tt.setToX(-1.0 * msg.getLayoutBounds().getWidth());
		tt.setCycleCount(TranslateTransition.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
	}
}
