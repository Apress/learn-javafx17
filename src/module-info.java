module JavaFXBook {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.web;
	requires javafx.fxml;
	requires jdk.jsobject;
	
	opens com.jdojo.animation to javafx.graphics, javafx.base;
	opens com.jdojo.binding to javafx.graphics, javafx.base;
	opens com.jdojo.canvas to javafx.graphics, javafx.base;
	opens com.jdojo.chart to javafx.graphics, javafx.base;
	opens com.jdojo.collections to javafx.graphics, javafx.base;
	opens com.jdojo.color to javafx.graphics, javafx.base;
	opens com.jdojo.concurrent to javafx.graphics, javafx.base;
	opens com.jdojo.container to javafx.graphics, javafx.base;
	opens com.jdojo.control to javafx.graphics, javafx.base;
	opens com.jdojo.dnd to javafx.graphics, javafx.base;
	opens com.jdojo.effect to javafx.graphics, javafx.base;
	opens com.jdojo.event to javafx.graphics, javafx.base;
	opens com.jdojo.fxml to javafx.graphics, javafx.base;
	opens com.jdojo.image to javafx.graphics, javafx.base;
	opens com.jdojo.intro to javafx.graphics, javafx.base;
	opens com.jdojo.media to javafx.graphics, javafx.base;
	opens com.jdojo.mvc to javafx.graphics, javafx.base;
	opens com.jdojo.mvc.model to javafx.graphics, javafx.base;
	opens com.jdojo.mvc.view to javafx.graphics, javafx.base;
	opens com.jdojo.node to javafx.graphics, javafx.base;
	opens com.jdojo.print to javafx.graphics, javafx.base;
	opens com.jdojo.scene to javafx.graphics, javafx.base;
	opens com.jdojo.shape to javafx.graphics, javafx.base;
	opens com.jdojo.shape3d to javafx.graphics, javafx.base;
	opens com.jdojo.stage to javafx.graphics, javafx.base;
	opens com.jdojo.style to javafx.graphics, javafx.base;
	opens com.jdojo.transform to javafx.graphics, javafx.base;
	opens com.jdojo.web to javafx.graphics, javafx.base;

}