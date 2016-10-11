package BrazilCenter.ProcessingService.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 */
public class XMLOperator {

	private String filePath = "ProcessConfig.xml";
	private Configuration conf;

	public Configuration getConfiguration() {
		return this.conf;
	}

	public XMLOperator() {
		this.conf = new Configuration();
	}

	/**
	 */
	public boolean Initial() {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(filePath);

			NodeList items = document.getChildNodes();
			for (int i = 0; i < items.getLength(); i++) {
				Node value = items.item(i);
				NodeList values = value.getChildNodes();
				for (int j = 0; j < values.getLength(); j++) {
					Node tmp = values.item(j);
					String strvalue = tmp.getTextContent();
					if (tmp.getNodeName().compareTo("SoftwareId") == 0) {
						this.conf.setSoftwareId(strvalue);
 					} else if(tmp.getNodeName().compareTo("StoreRootDir") == 0){
 						this.conf.setRootDir(strvalue);
 					}else if (tmp.getNodeName().compareTo("MqServerInfo") == 0) {
						NodeList innernodelist = tmp.getChildNodes();
						for (int m = 0; m < innernodelist.getLength(); m++) {
							Node innernode = innernodelist.item(m);
							String inerstrvalue = innernode.getTextContent();
							if (innernode.getNodeName().compareTo("MqServerIP") == 0) {
								this.conf.setMqHostIp(inerstrvalue);
							} else if (innernode.getNodeName().compareTo("MqUserName") == 0) {
								this.conf.setMqUserName(inerstrvalue);
							} else if (innernode.getNodeName().compareTo("MqPasswd") == 0) {
								this.conf.setMqPasswd(inerstrvalue);
							} else {

							}
						}
					} else {

					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (SAXException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
		}
		return true;
	}
}
