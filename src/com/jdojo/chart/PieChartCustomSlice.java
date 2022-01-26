// PieChartCustomSlice.java
package com.jdojo.chart;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PieChartCustomSlice extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		PieChart chart = new PieChart();
		chart.setTitle("Population in 2000");

		// Place the legend on the left side
		chart.setLegendSide(Side.LEFT);
		
		// Set the data for the chart
		ObservableList<PieChart.Data> chartData = PieChartUtil.getChartData();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		
		// Set a CSS for the scene
		// TODO: fix in book
		scene.getStylesheets().addAll(ResourceUtil.getResourceURLStr("css/pie_slice.css"));

		stage.setScene(scene);
		stage.setTitle("Custom Pie Slices");
		stage.show();
	}
}
