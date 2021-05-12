import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static Timer timer;
	private static TimerTask timerTask;
	private static int minFPS;
	
	public static void main(String[] args) {
		controller();
	}
	
	public static void controller() {
		Scanner s = new Scanner(System.in);
		System.out.println("Type 'start' to begin tracking system statistics or 'quit' to end the program");
		
		String in = s.nextLine();
		
		System.out.println("Below what FPS should the program begin collecting data?");
		minFPS = s.nextInt();
				
		if(in.equals("start"))
			connect();
		else if(in.equals("quit"))
			System.out.println("End of Processing.");
		
		s.close();
	}

	public static void connect() {
		Scanner s = new Scanner(System.in);
		System.out.println("Beginning Processing...");
		
		timerTask = new StatsGetter(minFPS);
		timer = new Timer();
		
		timer.schedule(timerTask, 0, 5000);
		
		System.out.println("Type 'summary' to view your statistics");
		String in = s.nextLine();
		
		if(in.equals("summary")) {
			disconnect();
		}
	}
	
	public static void disconnect() {
		timer.cancel();
		StatsGetter sg = (StatsGetter)timerTask;
		sg.plot();
		
		System.out.println("Displaying Charts...");
		System.out.println("You FPS dropped below " + minFPS + ", " + sg.getNumDrops() + "times");
		System.out.println("End of Processing.");
	}
}
