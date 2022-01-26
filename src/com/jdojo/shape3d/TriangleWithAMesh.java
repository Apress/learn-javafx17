// TriangleWithAMesh.java
package com.jdojo.shape3d;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TriangleWithAMesh extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a MeshView and position ity in teh space
		MeshView meshView = this.createMeshView();		
		meshView.setTranslateX(250);
		meshView.setTranslateY(100);
		meshView.setTranslateZ(400);
		
		// Scale the Meshview to make it look bigger
		meshView.setScaleX(10.0);
		meshView.setScaleY(10.0);
		meshView.setScaleZ(10.0);

		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(-50);
		camera.setTranslateZ(300);

		// Add a Rotation animation to the camera
		RotateTransition rt = new RotateTransition(Duration.seconds(2), camera);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setFromAngle(-30);
		rt.setToAngle(30);
		rt.setAutoReverse(true);
		rt.setAxis(Rotate.Y_AXIS);
		rt.play();

		// Front light is red
		PointLight redLight = new PointLight();
		redLight.setColor(Color.RED);
		redLight.setTranslateX(250);
		redLight.setTranslateY(150);
		redLight.setTranslateZ(300);

		// Back light is green
		PointLight greenLight = new PointLight();
		greenLight.setColor(Color.GREEN);
		greenLight.setTranslateX(200);
		greenLight.setTranslateY(150);
		greenLight.setTranslateZ(450);

		Group root = new Group(meshView, redLight, greenLight);
		
		// Rotate the triangle with its lights to 90 degrees
		root.setRotationAxis(Rotate.Y_AXIS);
		root.setRotate(90);

		Scene scene = new Scene(root, 400, 300, true);
		scene.setCamera(camera);
		stage.setScene(scene);
		stage.setTitle("Creating a Triangle using a TriangleMesh");
		stage.show();
	}

	public MeshView createMeshView() {
		float[] points = {50, 0, 0,  // v0 (iv0 = 0)
		                  45, 10, 0, // v1 (iv1 = 1)
		                  55, 10, 0  // v2 (iv2 = 2)
		                 };

		float[] texCoords = { 0.5f, 0.5f, // t0 (it0 = 0)
		                      0.0f, 1.0f, // t1 (it1 = 1)
		                      1.0f, 1.0f  // t2 (it2 = 2)
		                    };

		int[] faces = {
			0, 0, 2, 2, 1, 1, // iv0, it0, iv2, it2, iv1, it1 (front face)
			0, 0, 1, 1, 2, 2  // iv0, it0, iv1, it1, iv2, it2 (back face)
		};

		// Create a TriangleMesh
		TriangleMesh mesh = new TriangleMesh();
		mesh.getPoints().addAll(points);
		mesh.getTexCoords().addAll(texCoords);
		mesh.getFaces().addAll(faces);
		
		// Create a NeshView
		MeshView meshView = new MeshView();
		meshView.setMesh(mesh);

		return meshView;
	}
}
