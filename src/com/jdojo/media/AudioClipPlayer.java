// AudioClipPlayer.java
package com.jdojo.media;

import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class AudioClipPlayer extends Application {
	private AudioClip audioClip;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() {
		URL mediaUrl = ResourceUtil.getResourceURL("media/chimes.wav");
		
		// Create an AudioClip, which loads the audio data synchronously
		audioClip = new AudioClip(mediaUrl.toExternalForm());
	}

	@Override
	public void start(Stage stage) {
		Button playBtn = new Button("Play");
		Button stopBtn = new Button("Stop");

		// Set event handlers for buttons
		playBtn.setOnAction(e -> audioClip.play());
		stopBtn.setOnAction(e -> audioClip.stop());
		
		HBox root = new HBox(5, playBtn, stopBtn);
		root.setStyle("-fx-padding: 10;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Playing Short Audio Clips");
		stage.show();
	}
}
