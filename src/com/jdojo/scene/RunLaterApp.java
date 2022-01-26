// RunLaterApp.java
package com.jdojo.scene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunLaterApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		System.out.println("init(): " + Thread.currentThread().getName());

		// Create a Runnable task
		Runnable task = () -> System.out.println("Running the task on the "
				+ Thread.currentThread().getName());

		// Submit the task to be run on the JavaFX Aplication Thread
		Platform.runLater(task);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(new Group(), 400, 100));
		stage.setTitle("Using Platform.runLater() Method");
		stage.show();
	}
}
