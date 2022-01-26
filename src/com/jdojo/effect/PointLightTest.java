// PointLightTest.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

public class PointLightTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a light source and position it in the space
		Light.Point light = new Light.Point(150.0, 50.0, 50.0, Color.WHITE);
		
		// Create a Lighting effect with the light source
		Lighting effect = new Lighting();
		effect.setLight(light);
		effect.setSurfaceScale(8.0);

		Text text = new Text();
		text.setText("Point");
		text.setFill(Color.RED);
		text.setFont(Font.font("null", FontWeight.BOLD, 72));
		text.setBoundsType(TextBoundsType.VISUAL);

		Rectangle rect = new Rectangle(300, 100);
		rect.setFill(Color.LIGHTGRAY);

		// Set the same Lighting effect to both Rectangle and Text nodes
		text.setEffect(effect);
		rect.setEffect(effect);

		StackPane sp = new StackPane(rect, text);
		BorderPane.setMargin(sp, new Insets(5));
		GridPane lightDirectionController = this.getPointLightUI(light);
		GridPane controllsrPane = LightingUtil.getPropertyControllers(effect);

		BorderPane root = new BorderPane();
		root.setCenter(sp);
		root.setRight(controllsrPane);
		root.setBottom(lightDirectionController);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Configuring a Point Light");
		stage.show();
	}
	
	private GridPane getPointLightUI(Light.Point light) {
		Slider xSlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getX(), light.xProperty());
		Slider ySlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getY(), light.yProperty());
		Slider zSlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getZ(), light.zProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		pane.addRow(0, new Label("x:"), xSlider);
		pane.addRow(1, new Label("y:"), ySlider);
		pane.addRow(2, new Label("z:"), zSlider);

		return pane;
	}
}
