import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
 
public class XPathTest
{
    public static void main(String[] args) throws Exception
    {
        //Build DOM
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("C:\\\\XML\\test.xml");
 
        //Create XPath
 
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
 
        System.out.println("List of countries");
 
        // 1) Get countries
        XPathExpression expr = xpath.compile("//chargeRatePlan/subscriberCurrency/applicableRum/crpRelDateRange/enhancedZoneModel/results[name='Kosovo Fixed Line']/name/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        int i=0;
        for (i = 0; i < nodes.getLength(); i++) {
        	NodeList myNodeList = (NodeList) xpath.compile("//chargeRatePlan/subscriberCurrency/applicableRum/crpRelDateRange/enhancedZoneModel/results/crpCompositePopModel/usageChargePopModel/priceTier/priceTierValidityPeriod/priceTierRange/scaledCharge/price/text()")
        	           .evaluate(doc, XPathConstants.NODESET);
        	myNodeList.item(0).setNodeValue("Hi mom!");
            System.out.println(myNodeList.item(i).getNodeValue());
        }
        System.out.println(i);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult resultnew = new StreamResult(new File("C:\\\\XML\\test.xml"));
		transformer.transform(source, resultnew);

    }
}