// PrimeFinderService.java
package com.jdojo.concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import static javafx.concurrent.Worker.State.RUNNING;
import static javafx.concurrent.Worker.State.SCHEDULED;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PrimeFinderService  extends Application {
	Button startBtn = new Button("Start");	
	Button cancelBtn = new Button("Cancel");	
	Button resetBtn = new Button("Reset");
	Button exitBtn = new Button("Exit");
	boolean onceStarted = false;
	
	// Create the service
	Service<ObservableList<Long>> service = new Service<ObservableList<Long>>() {
		@Override
		protected Task<ObservableList<Long>> createTask() {
			return new PrimeFinderTask();
		}
	};

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		addEventHandlders();

		// Enable disable buttons based on the service state
		bindButtonsState();
			
		GridPane pane = new WorkerStateUI(service);
		HBox buttonBox = new HBox(5, startBtn, cancelBtn, resetBtn, exitBtn);
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
		stage.setTitle("A Prime Number Finder Service");
		stage.show();
	}

	public void addEventHandlders() {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> {
			if (onceStarted) {
				service.restart();
			} else {
				service.start();
				onceStarted = true;
				startBtn.setText("Restart");
			}
		});

		cancelBtn.setOnAction(e -> service.cancel());
		resetBtn.setOnAction(e -> service.reset()); 
		exitBtn.setOnAction(e -> Platform.exit());
	}
	
	public void bindButtonsState() {
		cancelBtn.disableProperty().bind(service.stateProperty().isNotEqualTo(RUNNING));
		resetBtn.disableProperty().bind(
			Bindings.or(service.stateProperty().isEqualTo(RUNNING),
			            service.stateProperty().isEqualTo(SCHEDULED)));
	}
}
