// QuickMediaPlayer.java
package com.jdojo.media;

import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;

public class QuickMediaPlayer extends Application {	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Locate the media content
		URL mediaUrl = ResourceUtil.getResourceURL("media/gopro.mp4");
		String mediaStringUrl = mediaUrl.toExternalForm();

		// Create a Media
		Media media = new Media(mediaStringUrl);

		// Create a Media Player
		MediaPlayer player = new MediaPlayer(media);

		// Automatically begin the playback
		player.setAutoPlay(true);

		// Create a 400X300 MediaView
		MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(400);
		mediaView.setFitHeight(300);
		
		// Create Play and Stop player control buttons and add action
		// event handlers to them
		Button playBtn = new Button("Play");
		playBtn.setOnAction(e -> {
			if (player.getStatus() == PLAYING) {
				player.stop();
				player.play();
			} else {
				player.play();
			}
		});

		Button stopBtn = new Button("Stop");
		stopBtn.setOnAction(e -> player.stop());
		
		// Add an error handler
		player.setOnError(() -> System.out.println(player.getError().getMessage()));
		
		HBox controlBox = new HBox(5, playBtn, stopBtn);
		BorderPane root = new BorderPane();

		// Add the MediaView and player controls to the scene graph
		root.setCenter(mediaView);
		root.setBottom(controlBox);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Playing Media");
		stage.show();
	}
}
