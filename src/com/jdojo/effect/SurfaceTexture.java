// SurfaceTexture.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import javafx.stage.Stage;

public class SurfaceTexture extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text text = new Text();
		text.setText("Texture");
		text.setFill(Color.RED);
		text.setFont(Font.font(null, FontWeight.BOLD, 72));
		text.setBoundsType(TextBoundsType.VISUAL);

		Lighting effect = new Lighting();
		effect.setBumpInput(null); // Remove the default bumpInput
		text.setEffect(effect);

		// Let the user choose to use a bumpInput
		CheckBox bumpCbx = new CheckBox("Use a GaussianBlur Bump Input?");
		bumpCbx.selectedProperty().addListener((prop, oldValue,newValue) -> {
			if (newValue) {
				effect.setBumpInput(new GaussianBlur(20));
			}
			else {
				effect.setBumpInput(null);
			}
		});

		// Let the user select a surfaceScale
		Slider scaleSlider = new Slider(0.0, 10.0, 1.5);
		effect.surfaceScaleProperty().bind(scaleSlider.valueProperty());
		scaleSlider.setShowTickLabels(true);
		scaleSlider.setMajorTickUnit(2.0);
		scaleSlider.setShowTickMarks(true);

		VBox root = new VBox(10, text, bumpCbx, scaleSlider);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Surface Scale and Bump Input");
		stage.show();
	}
}
