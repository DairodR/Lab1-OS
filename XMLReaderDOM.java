package CreatingProcesses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReaderDOM {
// op een ander bestand lezen ze de filename in met een if else 
// wat het mogelijk maakt om al die bestanden via hetzelfde project in 
// te lezen wat geen slecht idee is
    public Queue<Process> leesProcessen(String filePath){	
    	Queue<Process> processen = new LinkedList<>();
    	
		 //kan via case/ switch structuur
    	File xmlFile = new File(filePath);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder;
	
    	try {
    		dBuilder = dbFactory.newDocumentBuilder();
    		Document doc = dBuilder.parse(xmlFile);
    		doc.getDocumentElement().normalize();
    		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    		NodeList nodeList = doc.getElementsByTagName("process");
    		//now XML is loaded as Document in memory, lets convert it to Object List
    		for(int i = 0; i < nodeList.getLength(); i++) {
    			processen.add(getProcess(nodeList.item(i)));
    		}
    		for (Process emp : processen) {
                System.out.println(emp.toString());
            }
    	}catch(SAXException | ParserConfigurationException | IOException e){
    		e.printStackTrace();;
    	}
    	return processen;
    }
    	private static Process getProcess(Node node) {
    		//XMLReaderDOM domReader = new XMLReaderDOM();
            Process emp = new Process();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                emp.setPid(Integer.parseInt(getTagValue("pid", element)));
                emp.setArrivaltime(Integer.parseInt(getTagValue("arrivaltime", element)));
                emp.setServicetime(Integer.parseInt(getTagValue("servicetime", element)));
                emp.setServicetimeneeded(emp.getServicetime());
                emp.setStarttime(-1);
            }

            return emp;	
        }
    	private static String getTagValue(String tag, Element element) {
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodeList.item(0);
            return node.getNodeValue();	
    	}   
}

