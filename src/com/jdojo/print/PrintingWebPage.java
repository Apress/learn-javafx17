// PrintingWebPage.java
package com.jdojo.print;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PrintingWebPage extends Application {
	String HOME_PAGE = "http://www.yahoo.com";

	TextField urlFld = new TextField();
	Button goBtn = new Button("Go");
	Button printBtn = new Button("Print");
	WebView webView = new WebView();
	WebEngine webEngine = webView.getEngine();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event hanlders
		addEventHandlers(stage);

		BorderPane root = new BorderPane();
		HBox top = new HBox(5, new Label("URL"), urlFld,  goBtn, printBtn);
		top.setPadding(new Insets(2, 5, 10, 5));
		root.setTop(top);

		root.setCenter(webView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		// Load the Home Page 
		webEngine.load(HOME_PAGE);
	}

	private void addEventHandlers(Stage stage) {
		// Update the stage title when a new web page title is available
		webEngine.titleProperty().addListener((prop, oldTitle, newTitle) -> {
			stage.setTitle(newTitle);
		});

		// Add event handler for GO button
		goBtn.setOnAction(e -> {
			webEngine.load(urlFld.getText());
		});
	
		printBtn.setOnAction(e -> print(stage));

		// Enable the print button and sync teh URL
		webEngine.getLoadWorker().stateProperty().addListener(
				(prop, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) { 
				String newLocation = webEngine.getLocation();
				urlFld.setText(newLocation);
				printBtn.setDisable(false);
			} else {
				printBtn.setDisable(true);
			}
		});
	}
	
	private void print(Stage stage) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job == null) {
			return;
		}

		// Show the print setup dialog
		boolean proceed = job.showPrintDialog(stage);
		if (proceed) {
			webEngine.print(job);
			job.endJob();
		}
	}
}
