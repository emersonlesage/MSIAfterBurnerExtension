import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.TimerTask;

public class StatsGetter extends TimerTask{

	private final String USERNAME = "MSIAfterburner";
	private final String PASSWORD =  "17cc95b4017d496f82";
	private final String URLSTRING = "http://localhost:82/mahm";
	private double minFPS;
	private Unmarshaller um;
	private Trigger tr;
	private ArrayList<KVP> data;
	private boolean isConcur;
	
	public StatsGetter(int fps) {
		um = new Unmarshaller();
		data = new ArrayList<KVP>();
		minFPS = fps;
		isConcur = false;
	}

	@Override
	public void run() {
		completeTask();
	}
	
	public void completeTask() {
		URL url;
		InputStream is;
		
		
		try {
			url = new URL(URLSTRING);
			URLConnection uc = url.openConnection();
			
			
			String userpass = USERNAME + ":" + PASSWORD;
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));	//encoding 
			uc.setRequestProperty ("Authorization", basicAuth);	//enter username and password
		
			is = uc.getInputStream();	//get xml file
			
			data = um.unmarshal(is); 
			
			double fps = data.get(data.size() - 1).getValue();
			
			if(fps < minFPS && !isConcur) {
				tr = new Trigger();
				tr.storeData(data);
				isConcur = true;
				
			}else if(fps < minFPS && isConcur) {
				tr.storeData(data);
				
			}else {
				isConcur = false;
			}
			
		} catch (MalformedURLException e) {
            e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public int getNumDrops() {
		return tr.getNumDrops();
	}
	
	public void plot() {
		Graph g = tr.getPlot();
		
		g.plotFPS();
		g.plotTemp();
		g.plotUsage();
	}
}
