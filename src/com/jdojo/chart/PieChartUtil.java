// PieChartUtil.java
package com.jdojo.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class PieChartUtil {
	public static ObservableList<PieChart.Data> getChartData() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		data.add(new PieChart.Data("China", 1275));
		data.add(new PieChart.Data("India", 1017));
		data.add(new PieChart.Data("Brazil", 172));
		data.add(new PieChart.Data("UK", 59));
		data.add(new PieChart.Data("USA", 285));
		return data;
	}
}
