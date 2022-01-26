// PieChartExtraColor.java
package com.jdojo.chart;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PieChartExtraColor extends Application {
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
		this.addData(chartData);
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		
		// TODO: fix in book
		scene.getStylesheets()
			 .add(ResourceUtil.getResourceURLStr("css/additional_series_colors.css"));
		stage.setScene(scene);
		stage.setTitle("A Pie Chart with over 8 Slices");
		stage.show();

		// Override the default series color style class-name for slices over 8. 
		// Works only when you set it after the scene is visible
		this.setSeriesColorStyles(chart);
	}

	private void addData(ObservableList<PieChart.Data> data) {
		data.add(new PieChart.Data("Bangladesh", 138));
		data.add(new PieChart.Data("Egypt", 68));
		data.add(new PieChart.Data("France", 59));
		data.add(new PieChart.Data("Germany", 82));
		data.add(new PieChart.Data("Indonesia", 212));
	}

	private void setSeriesColorStyles(PieChart chart) {
		ObservableList<PieChart.Data> chartData = chart.getData();
		int size = chartData.size();		
		for (int i = 8; i < size; i++) {
			String removedStyle = "default-color" + (i % 8);
			String addedStyle = "default-color" + (i % size);

			// Reset the pie slice colors
			Node node = chartData.get(i).getNode();
			node.getStyleClass().remove(removedStyle);
			node.getStyleClass().add(addedStyle);

			// Reser the legend colors
			String styleClass = ".pie-legend-symbol.data" + i + 
			                    ".default-color" + (i % 8);
			Node legendNode = chart.lookup(styleClass);
			if (legendNode != null) {
				legendNode.getStyleClass().remove(removedStyle);
				legendNode.getStyleClass().add(addedStyle);
			}
		}
	}
}
