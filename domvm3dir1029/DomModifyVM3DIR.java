package domvm3dir1029;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyVM3DIR {
    
    public static void main(String[] args) {
        try {
        
        // Beolvassuk az xml fájlt
        File inputFile = new File("VM3DIR_1105/VM3DIRhallgato.xml");
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setValidating(false);
        docFactory.setNamespaceAware(true);
        try {
            docFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            docFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {
            // Ignore if features are not supported
        }
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        // Keresés az id="01" hallgatóra
        NodeList hallgatoList = doc.getElementsByTagName("hallgato");
        Node hallgat = null;
        
        for (int i = 0; i < hallgatoList.getLength(); i++) {
            Node node = hallgatoList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                if ("01".equals(elem.getAttribute("id"))) {
                    hallgat = node;
                    break;
                }
            }
        }
        
        if (hallgat == null) {
            System.out.println("Nem található hallgató id=\"01\"-gyel!");
            return;
        }
        
        NodeList list = hallgat.getChildNodes(); 

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                
                if ("keresztnev".equals(eElement.getNodeName())) {
                    if ("Pál".equals(eElement.getTextContent())) {
                        eElement.setTextContent("Olivia");
                    }
                }

                if ("vezeteknev".equals(eElement.getNodeName())) {
                    if ("Kiss".equals(eElement.getTextContent())) {
                        eElement.setTextContent("Erős");
                    }
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        System.out.println("=== XML tartalom konzolra írása ===");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);

    } catch (Exception e) { 
        e.printStackTrace();
    }
    }
}

