// HoppingText.java
package com.jdojo.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HoppingText extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text msg = new Text("Hopping text!");
		msg.setTextOrigin(VPos.TOP);
		msg.setFont(Font.font(24));
		
		Pane root = new Pane(msg);
		root.setPrefSize(500, 70);
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Hopping Text");
		stage.show();
	
		// Setup a Timeline animation
		double start = scene.getWidth();
		double end = -1.0 * msg.getLayoutBounds().getWidth();

		KeyFrame[] frame = new KeyFrame[11];
		for(int i = 0; i <= 10; i++) {
			double pos = start - (start - end) * i / 10.0;

			// Set 2.0 seconds as the cycle duration
			double duration = i/5.0;
			
			// Use a discrete interpolator
			KeyValue keyValue = new KeyValue(msg.translateXProperty(), 
			                                 pos , 
			                                 Interpolator.DISCRETE);
			frame[i] = new KeyFrame(Duration.seconds(duration), keyValue);
		}

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}
}
