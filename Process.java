import java.util.ArrayList;

public class Process {
	
	ArrayList<KVP> data;

	public Process(ArrayList<KVP> in) {
		data = in;
	}
	
	public void capture() {
		System.out.println(data);
	}
}
