import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static Timer timer;
	private static TimerTask timerTask;
	
	public static void main(String[] args) {
		System.out.println("Starting Process...");
		connect();
	}

	public static void connect() {
		timerTask = new DataGetter();
		timer = new Timer();
		
		timer.schedule(timerTask, 0, 5000);
	}
}
