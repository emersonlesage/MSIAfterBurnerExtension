import java.util.ArrayList;

public class Trigger {

	private static final int MAX_SIZE = 100;
	private static int timesDropped = 0;
	private int counter;
	
	private ArrayList<Double> gpuTemp;
	private ArrayList<Double> cpuTemp;
	private ArrayList<Double> gpuUse;
	private ArrayList<Double> cpuUse;
	private ArrayList<Double> ramUse;
	private ArrayList<Double> curFPS;
	
	public int getNumDrops() {
		return timesDropped;
	}
	
	public Trigger() {
		counter = 0;
		timesDropped++;
		
		//TODO
		gpuTemp = new ArrayList<Double>();
		cpuTemp = new ArrayList<Double>();
		cpuUse = new ArrayList<Double>();
		gpuUse = new ArrayList<Double>();
		ramUse = new ArrayList<Double>();
		curFPS = new ArrayList<Double>();
	}
	
	public void storeData(ArrayList<KVP> data) {
		gpuTemp.add(data.get(0).getValue());
		gpuUse.add(data.get(1).getValue());
		cpuTemp.add(data.get(2).getValue());
		cpuUse.add(data.get(3).getValue());
		ramUse.add(data.get(4).getValue());
		curFPS.add(data.get(5).getValue());
		
		counter++;
	}
	
	public Graph getPlot() {
		Graph g = new Graph(gpuTemp, gpuUse, cpuTemp, cpuUse, ramUse, curFPS);
		return g;
	}
}
