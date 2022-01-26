// PieSliceTest.java
package com.jdojo.chart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PieSliceTest extends Application {
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

		// Add a Tooltip to all pie slices
		this.addSliceTooltip(chart);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customizing Pie Slices");
		stage.show();
	}

	private void addSliceTooltip(PieChart chart) {
		// Compute the total pie value
		double totalPieValue = 0.0;
		for (PieChart.Data d : chart.getData()) {
			totalPieValue += d.getPieValue();
		}

		// Add a tooltip to all pie slices
		for (PieChart.Data d : chart.getData()) {
			Node sliceNode = d.getNode();
			double pieValue = d.getPieValue();
			double percentPieValue = (pieValue / totalPieValue) * 100;

			// Create and install a Tooltip for the slice
			String msg = d.getName() + "=" + pieValue + 
			             " (" + String.format("%.2f", percentPieValue) + "%)";
			Tooltip tt = new Tooltip(msg);
			tt.setStyle("-fx-background-color: yellow;" + 
			            "-fx-text-fill: black;");
			Tooltip.install(sliceNode, tt);
		}
	}
}
