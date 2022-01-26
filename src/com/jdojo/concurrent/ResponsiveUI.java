// ResponsiveUI.java
package com.jdojo.concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResponsiveUI extends Application {
	Label statusLbl = new Label("Not Started...");
	Button startBtn = new Button("Start");	
	Button exitBtn = new Button("Exit");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> startTask());
		exitBtn.setOnAction(e -> stage.close());

		HBox buttonBox = new HBox(5, startBtn, exitBtn);
		VBox root = new VBox(10, statusLbl, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Responsive UI");
		stage.show();
	}

	public void startTask() {
		// Create a Runnable
		Runnable task = () -> runTask();

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);

		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);

		// Start the thread
		backgroundThread.start();
	}
	
	public void runTask() {
		for(int i = 1; i <= 10; i++) {
			try {
				String status = "Processing " + i + " of " + 10;

				// Update the Label on the JavaFx Application Thread
				Platform.runLater(() -> statusLbl.setText(status));
			
				System.out.println(status);
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
