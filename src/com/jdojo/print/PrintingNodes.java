// PrintingNodes.java
package com.jdojo.print;

import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrintingNodes  extends Application {
	private Label jobStatus = new Label();
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		VBox root = new VBox(5); 

		Label textLbl = new Label("Text:");
		TextArea text = new TextArea();
		text.setPrefRowCount(10);
		text.setPrefColumnCount(20);
		text.setWrapText(true);

		// Button to print the TextArea node	
		Button printTextBtn = new Button("Print Text");
		printTextBtn.setOnAction(e -> print(text));

		// Button to print the entire scene
		Button printSceneBtn = new Button("Print Scene");
		printSceneBtn.setOnAction(e -> print(root));

		HBox jobStatusBox = new HBox(5, new Label("Print Job Status:"), jobStatus);
		HBox buttonBox = new HBox(5, printTextBtn, printSceneBtn);

		root.getChildren().addAll(textLbl, text, jobStatusBox, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Printing Nodes");
		stage.show();
	}

	private void print(Node node) {
		jobStatus.textProperty().unbind();
		jobStatus.setText("Creating a printer job...");

		// Create a printer job for teh default printer
		PrinterJob job = PrinterJob.createPrinterJob(); 
		if (job != null) {
			// Show the printer job status
			jobStatus.textProperty().bind(job.jobStatusProperty().asString());
			
			// Print the node
			boolean printed = job.printPage(node);
			if (printed) { 
				// End the printer job
				job.endJob();
			} else {
				jobStatus.textProperty().unbind();
				jobStatus.setText("Printing failed.");
			}
		} else {
			jobStatus.setText("Could not create a printer job.");
		}
	}
}
