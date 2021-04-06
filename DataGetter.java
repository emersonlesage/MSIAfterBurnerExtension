import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataGetter extends TimerTask{
	
	private String username;
	private String password;
	private String urlString;
	private Process p;
	
	public DataGetter() {
		username = "MSIAfterburner";
		password = "17cc95b4017d496f82";
		urlString = "http://localhost:82/mahm";
	}

	@Override
	public void run() {
		completeTask();
	}
	
	public void completeTask() {
		URL url;
		InputStream is;
		ArrayList<KVP> data = new ArrayList<KVP>();
		
		try {
			url = new URL(urlString);
			URLConnection uc = url.openConnection();
			
			
			String userpass = username + ":" + password;
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));	//encoding 
			uc.setRequestProperty ("Authorization", basicAuth);	//enter username and password
		
			is = uc.getInputStream();	//get xml file
			
			data = parse(is);
			
			p = new Process(data);
			p.capture();
			
			
			
		}catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<KVP> parse(InputStream is){ 
		
		ArrayList<KVP> outputs = new ArrayList<KVP>();
		//build DOM object
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(is);
			
			document.getDocumentElement().normalize();
			
			//get all "HardwareMonitorEntry" elements
			NodeList nList = document.getElementsByTagName("HardwareMonitorEntry");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node node = nList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
			    
					Element eElement = (Element) node;
					String s = eElement.getElementsByTagName("localizedSrcName").item(0).getTextContent();
					double d = Double.parseDouble(eElement.getElementsByTagName("data").item(0).getTextContent());
					
					outputs.add(new KVP(s,d));
				}
			}
			
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return outputs;
	}

}
