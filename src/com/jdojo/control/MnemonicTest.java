// MnemonicTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MnemonicTest  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		Label msg = new Label("Press Ctrl + X on Windows \nand " +
		                      "\nMeta + X on Mac to close the window");
		Label lbl = new Label("Press Alt + 1 or Alt + 2");

		// Use Alt + 1 as the mnemonic for Button 1
		Button btn1 = new Button("Button _1");
		btn1.setOnAction(e -> lbl.setText("Button 1 clicked!"));

		// Use Alt + 2 as the mnemonic key for Button 2
		Button btn2 = new Button("Button 2");
		btn2.setOnAction(e -> lbl.setText("Button 2 clicked!"));
		KeyCombination kc = new KeyCodeCombination(KeyCode.DIGIT2, 
		                                           KeyCombination.ALT_DOWN);
		Mnemonic mnemonic = new Mnemonic(btn2, kc);
		scene.addMnemonic(mnemonic);

		// Add an accelarator key to the scene
		KeyCombination kc4= new KeyCodeCombination(KeyCode.X, 
		                                           KeyCombination.SHORTCUT_DOWN);
		Runnable task = () -> scene.getWindow().hide();
		scene.getAccelerators().put(kc4, task);
		
		// Add all children to the VBox
		root.getChildren().addAll(msg, lbl, btn1, btn2);
		
		stage.setScene(scene);
		stage.setTitle("Using Mnemonics and Accelerators");
		stage.show();
	}
}
