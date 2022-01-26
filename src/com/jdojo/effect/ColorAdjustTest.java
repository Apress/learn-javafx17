// ColorAdjustTest.java
package com.jdojo.effect;

import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// TODO: broken
public class ColorAdjustTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorAdjust effect = new ColorAdjust();

		Node node = getImageNode();
		node.setEffect(effect);

		GridPane controller = getController(effect);

		BorderPane root = new BorderPane();
		root.setCenter(node);
		root.setBottom(controller);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the ColorAdjust Effect");
		stage.show();
	}

	private Node getImageNode() {
		Node node = null;
		URL url = ResourceUtil.getResourceURL("picture/randomness.jpg");

		if (url != null) {
			node = new ImageView(url.toExternalForm());
		} else {
			System.out.println("Missing image file " + "picture/randomness.jpg");
			node = new StackPane(new Rectangle(100, 50, Color.LIGHTGRAY), 
			                     new Text("Color Adjust"));
		}
		return node;
	}

	private GridPane getController(ColorAdjust effect) {
		Slider hueSlider = new Slider(-1.0, 1.0, 0.0);
		effect.hueProperty().bind(hueSlider.valueProperty());

		Slider saturationSlider = new Slider(-1.0, 1.0, 0.0);
		effect.saturationProperty().bind(saturationSlider.valueProperty());

		Slider brightnessSlider = new Slider(-1.0, 1.0, 0.0);
		effect.brightnessProperty().bind(brightnessSlider.valueProperty());

		Slider contrastSlider = new Slider(-1.0, 1.0, 0.0);
		effect.contrastProperty().bind(contrastSlider.valueProperty());

		Slider[] sliders = new Slider[] {hueSlider, saturationSlider, 
		                                 brightnessSlider, contrastSlider};
		for (Slider s : sliders) {
			s.setPrefWidth(300);
			s.setMajorTickUnit(0.10);
			s.setShowTickMarks(true);
			s.setShowTickLabels(true);
		}

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("Hue:"), hueSlider);
		pane.addRow(1, new Label("Saturation:"), saturationSlider);
		pane.addRow(2, new Label("Brightness:"), brightnessSlider);
		pane.addRow(3, new Label("Contrast:"), contrastSlider);

		return pane;
	}
}
