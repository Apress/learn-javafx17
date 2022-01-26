// NodeSnapshot.java
package com.jdojo.image;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NodeSnapshot extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();

		Label nameLbl = new Label("Name:");
		TextField nameField = new TextField("Prema");

		Button syncSnapshotBtn = new Button("Synchronous Snapshot");
		syncSnapshotBtn.setOnAction(e -> syncSnapshot(nameField));

		Button asyncSnapshotBtn = new Button("Asynchronous Snapshot");
		asyncSnapshotBtn.setOnAction(e -> asyncSnapshot(nameField));

		root.setHgap(10);
		root.addRow(0, nameLbl, nameField, syncSnapshotBtn);
		root.add(asyncSnapshotBtn, 2, 1);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Taking a Snapshot of a Node");
		stage.show();
	}
	
	private void syncSnapshot(Node node) {
		SnapshotParameters params = getParams();
		WritableImage image = node.snapshot(params, null);
		ImageUtil.saveToFile(image);
	}
	
	private void asyncSnapshot(Node node) {
		// Create a Callback. Its call() method is called when 
		// the snapshot is ready. The getImage() method returns the snapshot
		Callback<SnapshotResult, Void> callback = (SnapshotResult result) -> {
			WritableImage image = result.getImage();
			ImageUtil.saveToFile(image);
			return null;
		};
		
		SnapshotParameters params = getParams();
		node.snapshot(callback, params, null);
	}
	
	private SnapshotParameters getParams() {
		// Set the fill to red and rotate the node by 30 degrees
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.RED);
		Transform tf = new Scale(0.8, 0.8);
		tf = tf.createConcatenation(new Rotate(10));
		params.setTransform(tf);
		return params;
	}
}
