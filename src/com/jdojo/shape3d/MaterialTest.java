// MaterialTest.java
package com.jdojo.shape3d;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class MaterialTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Box
		Box box = new Box(100, 100, 100);

		// Set the material for the box
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.TAN);
		box.setMaterial(material);

		// Place the box in the space
		box.setTranslateX(250);
		box.setTranslateY(0);
		box.setTranslateZ(400);

		// Create a Box with texture
		Box boxWithTexture = new Box(100, 100, 100);
		PhongMaterial textureMaterial = new PhongMaterial();
		Image randomness = new Image(ResourceUtil.getResourceURLStr("picture/randomness.jpg"));
		textureMaterial.setDiffuseMap(randomness);
		boxWithTexture.setMaterial(textureMaterial);

		// Place the box in the space
		boxWithTexture.setTranslateX(450);
		boxWithTexture.setTranslateY(-5);
		boxWithTexture.setTranslateZ(400);

		PointLight light = new PointLight();
		light.setTranslateX(250);
		light.setTranslateY(100);
		light.setTranslateZ(300);

		Group root = new Group(box, boxWithTexture);

		// Craete a Scene with depth buffer enabled
		Scene scene = new Scene(root, 300, 100, true);

		// Set a camera to view the 3D shapes
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(200);
		camera.setTranslateY(-50);
		camera.setTranslateZ(325);
		scene.setCamera(camera);

		stage.setScene(scene);
		stage.setTitle("Using Material Color and Texture for 3D Surface");
		stage.show();
	}
}
