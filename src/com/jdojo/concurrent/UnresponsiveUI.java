// UnresponsiveUI.java
package com.jdojo.concurrent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnresponsiveUI extends Application {
	Label statusLbl = new Label("Not Started...");
	Button startBtn = new Button("Start");
	Button exitBtn = new Button("Exit");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> runTask());
		exitBtn.setOnAction(e -> stage.close());

		HBox buttonBox = new HBox(5, startBtn, exitBtn);
		VBox root = new VBox(10, statusLbl, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("An Unresponsive UI");
		stage.show();
	}

	public void runTask() {
		for(int i = 1; i <= 10; i++) {
			try {
				String status = "Processing " + i + " of " + 10;
				statusLbl.setText(status);
				System.out.println(status);
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
