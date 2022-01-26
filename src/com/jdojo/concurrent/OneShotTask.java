// OneShotTask.java
package com.jdojo.concurrent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static javafx.concurrent.Worker.State.READY;
import static javafx.concurrent.Worker.State.RUNNING;

public class OneShotTask extends Application {
	Button startBtn = new Button("Start");	
	Button cancelBtn = new Button("Cancel");
	Button exitBtn = new Button("Exit");
	
	// Create the task
	PrimeFinderTask task = new PrimeFinderTask();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> startTask());
		cancelBtn.setOnAction(e -> task.cancel());
		exitBtn.setOnAction(e -> stage.close());

		// Enable/Disable the Start and Cancel buttons
		startBtn.disableProperty().bind(task.stateProperty().isNotEqualTo(READY));
		cancelBtn.disableProperty().bind(task.stateProperty().isNotEqualTo(RUNNING));
		GridPane pane = new WorkerStateUI(task);
		HBox buttonBox = new HBox(5, startBtn, cancelBtn, exitBtn);
		BorderPane root = new BorderPane();
		root.setCenter(pane);
		root.setBottom(buttonBox);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Prime Number Finder Task");
		stage.show();
	}

	public void startTask() {
		// Schedule the task on a background thread 
		Thread backgroundThread = new Thread(task);
		backgroundThread.setDaemon(true);
		backgroundThread.start();
	}
}
