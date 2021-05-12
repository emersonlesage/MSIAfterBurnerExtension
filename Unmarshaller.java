import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Unmarshaller {
	
	private DocumentBuilderFactory factory;
	private final String ELEMENT = "HardwareMonitorEntry";
	private final String ATTRIBUTE1 = "localizedSrcName";
	private final String ATTRIBUTE2 = "data";
	
	public Unmarshaller() {
		factory = DocumentBuilderFactory.newInstance();
	}
	
	public ArrayList<KVP> unmarshal(InputStream is) {
		
		ArrayList<KVP> outputs = new ArrayList<KVP>();
		
		try {
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
					
					if(checkTags(s)) {
						if(s.equalsIgnoreCase("RAM Usage"))
							d = (d / (16.0 * 1000.0)) * 100;				//percentage of total RAM
						
						outputs.add(new KVP(s,d));
					}
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
	
	private boolean checkTags(String s) {
		
		boolean result = false;
		
		if(s.equalsIgnoreCase("GPU Temperature") || s.equalsIgnoreCase("GPU Usage") || s.equalsIgnoreCase("CPU Temperature") 
				|| s.equalsIgnoreCase("CPU Usage") || s.equalsIgnoreCase("RAM Usage") || s.equalsIgnoreCase("Framerate")) {
			result = true;
		}
		
		return result;
	}
}
