
public class KVP {

	private String key;
	private double value;
	
	public KVP(String k, double v) {
		key = k;
		value = v;
	}
	
	public String getKey() {
		return key;
	}
	
	public double getValue() {
		return value;
	}
	
	public String toString() {
		return key + ": " + value;
	}
}
