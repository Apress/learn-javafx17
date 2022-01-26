// BlendTest.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlendTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorInput topInput = new ColorInput(0, 0, 100, 50, Color.LIGHTGREEN);
		ColorInput bottomInput = new ColorInput(50, 25, 100, 50, Color.PURPLE);

		// Create the Blend effect 
		Blend effect = new Blend();
		effect.setTopInput(topInput);
		effect.setBottomInput(bottomInput);

		Rectangle rect = new Rectangle(150, 75);
		rect.setEffect(effect);

		GridPane controller = this.getController(effect);

		HBox root = new HBox(rect, controller);
		root.setSpacing(30);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Blend Effect");
		stage.show();
	}

	private GridPane getController(Blend effect) {
		ComboBox<BlendMode> blendModeList = new ComboBox<>();
		blendModeList.setValue(effect.getMode());
		blendModeList.getItems().addAll(BlendMode.values());
		effect.modeProperty().bind(blendModeList.valueProperty());

		Slider opacitySlider = new Slider (0, 1.0, 1.0);
		opacitySlider.setMajorTickUnit(0.10);
		opacitySlider.setShowTickMarks(true);
		opacitySlider.setShowTickLabels(true);
		effect.opacityProperty().bind(opacitySlider.valueProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("Blend Mode:"), blendModeList);
		pane.addRow(1, new Label("Opacity:"), opacitySlider);

		return pane;
	}
}
