package Com.AKP.Utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class GetProperties_WebElement 
{
	public static String GetXMLTagValue(String xmlpath, String tagname) {

		String val = null;
		try {
			File f = new File(xmlpath);
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = docBuilder.parse(f);
			val = doc.getElementsByTagName(tagname).item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
}
