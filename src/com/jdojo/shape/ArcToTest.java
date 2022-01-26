// ArcToTest.java
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

public class ArcToTest extends Application {	
	private ArcTo arcTo;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create the ArcTo path element
		arcTo = new ArcTo();
		
		// Use the arcTo element to build a Path
		Path path = new Path(new MoveTo(0, 0),
							 new VLineTo(100), 
							 new HLineTo(100), 
							 new VLineTo(50),
							 arcTo);
		
		BorderPane root = new BorderPane();
		root.setTop(this.getTopPane());
		root.setCenter(path);		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ArcTo Path Elements");
		stage.show();
	}
	
	private GridPane getTopPane() {
		CheckBox largeArcFlagCbx = new CheckBox("largeArcFlag");
		CheckBox sweepFlagCbx = new CheckBox("sweepFlag");
		Slider xRotationSlider = new Slider(0, 360, 0);
		xRotationSlider.setPrefWidth(300);
		xRotationSlider.setBlockIncrement(30);
		xRotationSlider.setShowTickMarks(true);
		xRotationSlider.setShowTickLabels(true);

		Slider radiusXSlider = new Slider(100, 300, 100);
		radiusXSlider.setBlockIncrement(10);
		radiusXSlider.setShowTickMarks(true);
		radiusXSlider.setShowTickLabels(true);

		Slider radiusYSlider = new Slider(100, 300, 100); 
		radiusYSlider.setBlockIncrement(10);
		radiusYSlider.setShowTickMarks(true);
		radiusYSlider.setShowTickLabels(true);

		// Bind ArcTo properties to the control data
		arcTo.largeArcFlagProperty().bind(largeArcFlagCbx.selectedProperty());
		arcTo.sweepFlagProperty().bind(sweepFlagCbx.selectedProperty());
		arcTo.XAxisRotationProperty().bind(xRotationSlider.valueProperty());
		arcTo.radiusXProperty().bind(radiusXSlider.valueProperty());
		arcTo.radiusYProperty().bind(radiusYSlider.valueProperty());
		
		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, largeArcFlagCbx, sweepFlagCbx);
		pane.addRow(1, new Label("XAxisRotation"), xRotationSlider);
		pane.addRow(2, new Label("radiusX"), radiusXSlider);
		pane.addRow(3, new Label("radiusY"), radiusYSlider);

		return pane;
	}
}
