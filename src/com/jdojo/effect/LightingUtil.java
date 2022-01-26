// LightingUtil.java
package com.jdojo.effect;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;

public class LightingUtil {
	public static GridPane getPropertyControllers(Lighting effect) {
		Slider surfaceScaleSlider = getSlider(0.0, 10.0,
				effect.getSurfaceScale(), effect.surfaceScaleProperty());
		Slider diffuseConstantSlider = getSlider(0.0, 2.0,
				effect.getDiffuseConstant(), effect.diffuseConstantProperty());
		Slider specularConstantSlider = getSlider(0.0, 2.0,
				effect.getSpecularConstant(), effect.specularConstantProperty());
		Slider specularExponentSlider = getSlider(0.0, 40.0,
				effect.getSpecularExponent(), effect.specularExponentProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		pane.addRow(0, new Label("Surface Scale:"), surfaceScaleSlider);
		pane.addRow(1, new Label("Diffuse Constant:"), diffuseConstantSlider);
		pane.addRow(2, new Label("Specular Constant:"), specularConstantSlider);
		pane.addRow(3, new Label("Specular Exponent:"), specularExponentSlider);

		return pane;
	}

	public static Slider getSlider(double min, double max, double value, DoubleProperty prop) {
		Slider slider = new Slider(min, max, value);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(max / 4.0);
		prop.bind(slider.valueProperty());
		return slider;
	}
}
