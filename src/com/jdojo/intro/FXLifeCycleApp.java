// FXLifeCycleApp.java
package com.jdojo.intro;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXLifeCycleApp extends Application {
	public FXLifeCycleApp() {
		String name = Thread.currentThread().getName();
		System.out.println("FXLifeCycleApp() constructor: " + name);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		String name = Thread.currentThread().getName();
		System.out.println("init() method: " + name);
	}

	@Override
	public void start(Stage stage) {
		String name = Thread.currentThread().getName();
		System.out.println("start() method: " + name);

		Scene scene = new Scene(new Group(), 200, 200);
		stage.setScene(scene);
		stage.setTitle("JavaFX Application Life Cycle");
		stage.show();
	}

	@Override
	public void stop() {
		String name = Thread.currentThread().getName();
		System.out.println("stop() method: " + name);
	}
}
