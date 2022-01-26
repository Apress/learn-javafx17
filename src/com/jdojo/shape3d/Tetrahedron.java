// Tetrahedron.java
package com.jdojo.shape3d;

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

public class Tetrahedron extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		MeshView meshView = this.createMeshView();
		meshView.setTranslateX(250);
		meshView.setTranslateY(50);
		meshView.setTranslateZ(400);

		meshView.setScaleX(10.0);
		meshView.setScaleY(20.0);
		meshView.setScaleZ(10.0);

		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(0);
		camera.setTranslateZ(100);

		PointLight redLight = new PointLight();
		redLight.setColor(Color.RED);
		redLight.setTranslateX(250);
		redLight.setTranslateY(-100);
		redLight.setTranslateZ(250);

		Group root = new Group(meshView, redLight);
		root.setRotationAxis(Rotate.Y_AXIS);
		root.setRotate(45);

		Scene scene = new Scene(root, 200, 150, true);
		scene.setCamera(camera);
		stage.setScene(scene);
		stage.setTitle("A Tetrahedron using a TriangleMesh");
		stage.show();
	}

	public MeshView createMeshView() {
		float[] points = {10, 10, 10, // v0 (iv0 = 0)
		                  20, 20, 0,  // v1 (iv1 = 1) 
		                  0, 20, 0,   // v2 (iv2 = 2)
		                  10, 20, 20   // v3 (iv3 = 3)
		                 };

		float[] texCoords = {
			0.50f, 0.33f, // t0 (it0 = 0)	
			0.25f, 0.75f, // t1 (it1 = 1)
			0.50f, 1.00f, // t2 (it2 = 2)
			0.66f, 0.66f, // t3 (it3 = 3)
			1.00f, 0.35f, // t4 (it4 = 4)
			0.90f, 0.00f, // t5 (it5 = 5)
			0.10f, 0.00f, // t6 (it6 = 6)
			0.00f, 0.35f  // t7 (it7 = 7)
		};

		int[] faces = { 
			0, 0, 2, 1, 1, 3, // f0 front-face
			0, 0, 1, 3, 2, 1, // f0 back-face
			0, 0, 1, 4, 3, 5, // f1 front-face
			0, 0, 3, 5, 1, 4, // f1 back-face
			0, 0, 3, 6, 2, 7, // f2 front-face
			0, 0, 2, 7, 3, 6, // f2 back-face
			1, 3, 3, 2, 2, 1, // f3 front-face
			1, 3, 2, 1, 3, 2, // f3 back-face
		};

		TriangleMesh mesh = new TriangleMesh();
		mesh.getPoints().addAll(points);
		mesh.getTexCoords().addAll(texCoords);
		mesh.getFaces().addAll(faces);
		
		MeshView meshView = new MeshView();
		meshView.setMesh(mesh);

		return meshView;
	}
}
