import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Graph {
	
	private double[] gpuTemp;
	private double[] cpuTemp;
	private double[] gpuUse;
	private double[] cpuUse;
	private double[] ramUse;
	private double[] fps;
	private double[] time;

	public Graph(ArrayList<Double> gpuTemp, ArrayList<Double> gpuUse, ArrayList<Double> cpuTemp, ArrayList<Double> cpuUse, ArrayList<Double> ramUse, ArrayList<Double> fps) {

		//convert arraylists to arrays
		this.gpuTemp = gpuTemp.stream().mapToDouble(Double::doubleValue).toArray();
		this.gpuUse = gpuUse.stream().mapToDouble(Double::doubleValue).toArray();
		this.cpuTemp = cpuTemp.stream().mapToDouble(Double::doubleValue).toArray();
		this.cpuUse = cpuUse.stream().mapToDouble(Double::doubleValue).toArray();
		this.ramUse = ramUse.stream().mapToDouble(Double::doubleValue).toArray();
		this.fps = fps.stream().mapToDouble(Double::doubleValue).toArray();
		
		fill();
	}
	
	private void fill() {
		time = new double[gpuTemp.length];
		
		for(int i = 0; i < time.length; i++) {
			time[i] = i + 1;
		}
	}

	public void plotFPS() {
		//create plots
		final XYChart plotTemp = new XYChartBuilder().width(600).height(400).title("FPS during drop").xAxisTitle("Time (5s intervals)").yAxisTitle("FPS").build();

		//set legend
		plotTemp.getStyler().setLegendPosition(LegendPosition.OutsideS);
		
		//add data
		plotTemp.addSeries("FPS", time, fps);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			  @Override
			  public void run() {

			    // Create and set up the window.
			    JFrame frame = new JFrame("FPS Plot");
			    frame.setLayout(new BorderLayout());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    // chart
			    JPanel chartPanel = new XChartPanel<XYChart>(plotTemp);
			    frame.add(chartPanel, BorderLayout.CENTER);

			    // Display the window.
			    frame.pack();
			    frame.setVisible(true);
			  }
		});
		
	}

	
	public void plotTemp() {
		//create plots
		final XYChart plotTemp = new XYChartBuilder().width(600).height(400).title("Temperature During FPS drop").xAxisTitle("Time (5s intervals)").yAxisTitle("Temperature").build();

		//set legend
		plotTemp.getStyler().setLegendPosition(LegendPosition.OutsideS);
		
		//add data
		plotTemp.addSeries("GPU Temp", time, gpuTemp);
		plotTemp.addSeries("CPU Temp", time, cpuTemp);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			  @Override
			  public void run() {

			    // Create and set up the window.
			    JFrame frame = new JFrame("Temperature Plot");
			    frame.setLayout(new BorderLayout());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    // chart
			    JPanel chartPanel = new XChartPanel<XYChart>(plotTemp);
			    frame.add(chartPanel, BorderLayout.CENTER);

			    // Display the window.
			    frame.pack();
			    frame.setVisible(true);
			  }
		});
		
	}
	
	public void plotUsage() {
		//create plots
		final XYChart plotUsage = new XYChartBuilder().width(600).height(400).title("Usage During FPS drop").xAxisTitle("Time (5s intervals)").yAxisTitle("Usage (%)").build();

		//set legend
		plotUsage.getStyler().setLegendPosition(LegendPosition.OutsideS);
		
		//add data
		plotUsage.addSeries("GPU Usage", time, gpuUse);
		plotUsage.addSeries("CPU Usage", time, cpuUse);
		plotUsage.addSeries("RAM Usage", time, ramUse);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			  @Override
			  public void run() {

			    // Create and set up the window.
			    JFrame frame = new JFrame("Usage Plot");
			    frame.setLayout(new BorderLayout());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    // chart
			    JPanel chartPanel = new XChartPanel<XYChart>(plotUsage);
			    frame.add(chartPanel, BorderLayout.CENTER);

			    // Display the window.
			    frame.pack();
			    frame.setVisible(true);
			  }
		});
		
	}
}
