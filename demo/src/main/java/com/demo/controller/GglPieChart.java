package com.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;

@Controller

public class GglPieChart
{
	@RequestMapping(value = "/piechart", method = RequestMethod.GET)
	public String drawPieChart(ModelMap model)
	{
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Window",
				"Window");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux",
				"Linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Others",
				"Others");

		PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
		pieChart.setTitle("Google Pie Chart", Color.BLACK, 15);
		pieChart.setSize(720, 360);
		pieChart.setThreeD(true);

		model.addAttribute("pieUrl", pieChart.toURLString());

		return "display";
	}
	
	
	@RequestMapping(value = "/barchart", method = RequestMethod.GET)
	public String drawBarChart(ModelMap model)
	{
		/*
		Double delta = maxProgress.doubleValue() - now.doubleValue();
		Color color = null;
		if (delta.doubleValue() <= highPercent.doubleValue())
			color = Color.GREEN;
		else if (delta.doubleValue() <= mediumPercent.doubleValue())
			color = Color.YELLOW;
		else
			color = Color.RED;
		BarChartPlot currentCoverage = Plots.newBarChartPlot(
			Data.newData(now.doubleValue()), color);
		BarChartPlot freeCoverage = Plots.newBarChartPlot(
			Data.newData(delta.doubleValue()), backgroundColor);
		BarChart barChart = GCharts.newBarChart(currentCoverage, freeCoverage);
		barChart.setSize(200, 30);
		barChart.setTransparency(100);
		barChart.setDataStacked(true);
		barChart.setHorizontal(true);
		return barChart.toURLString();
		
*/
		BarChartPlot currentCoverage = Plots.newBarChartPlot(
				Data.newData(5), Color.BLACK,"Medicine 1");
		BarChartPlot currentCoverage2 = Plots.newBarChartPlot(
				Data.newData(8), Color.RED,"Medicine 2");
		
		BarChart barChart =GCharts.newBarChart(currentCoverage,currentCoverage2);
				barChart.setTitle("Google Pie Chart", Color.BLACK, 15);
				barChart.setSize(720, 360);
				barChart.setHorizontal(true);
			

		model.addAttribute("barUrl", barChart.toURLString());

		return "display";
	}
}