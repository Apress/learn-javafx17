// NodeSizeSentinelValues.java
package com.jdojo.node;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NodeSizeSentinelValues extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		// Override the intrinsic width of the cancel button
		cancelBtn.setPrefWidth(100);

		VBox root = new VBox();
		root.getChildren().addAll(okBtn, cancelBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Overriding Node Sizes");
		stage.show();

		System.out.println("okBtn.getPrefWidth(): " + okBtn.getPrefWidth());
		System.out.println("okBtn.getMinWidth(): " + okBtn.getMinWidth());
		System.out.println("okBtn.getMaxWidth(): " + okBtn.getMaxWidth());

		System.out.println("cancelBtn.getPrefWidth(): " + cancelBtn.getPrefWidth());
		System.out.println("cancelBtn.getMinWidth(): " + cancelBtn.getMinWidth());
		System.out.println("cancelBtn.getMaxWidth(): " + cancelBtn.getMaxWidth());
	}
}
