// PrintDialogs.java
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

public class PrintDialogs extends Application {
	private final Label jobStatus = new Label();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label textLbl = new Label("Text:");
		TextArea text = new TextArea();
		text.setPrefRowCount(10);
		text.setPrefColumnCount(20);
		text.setWrapText(true);

		// Button to print the TextArea node	
		Button pageSetupBtn = new Button("Page Setup and Print");
		pageSetupBtn.setOnAction(e -> pageSetup(text, stage));

		// Button to print the entire scene
		Button printSetupBtn = new Button("Print Setup and Print");
		printSetupBtn.setOnAction(e -> printSetup(text, stage));

		HBox jobStatusBox = new HBox(5, new Label("Print Job Status:"), jobStatus);
		HBox buttonBox = new HBox(5, pageSetupBtn, printSetupBtn);

		VBox root = new VBox(5, textLbl, text, jobStatusBox, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Showing Print Dialogs");
		stage.show();
	}

	private void pageSetup(Node node, Stage owner) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job == null) {
			return;
		}

		// Show the page setup dialog
		boolean proceed = job.showPageSetupDialog(owner);
		if (proceed) {
			print(job, node);
		}
	}

	private void printSetup(Node node, Stage owner) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job == null) {
			return;
		}

		// Show the print setup dialog
		boolean proceed = job.showPrintDialog(owner);
		if (proceed) {
			print(job, node);
		}
	}

	private void print(PrinterJob job, Node node) {
		jobStatus.textProperty().bind(job.jobStatusProperty().asString());

		boolean printed = job.printPage(node);
		if (printed) {
			job.endJob();
		}
	}
}
