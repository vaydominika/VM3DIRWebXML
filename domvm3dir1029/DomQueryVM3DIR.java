package domvm3dir1029;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

class DomQueryVM3DIR {

    public static void main(String[] args) {
        try {
            File inputFile = new File("domvm3dir1029/hallgatoVM3DIR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            NodeList hallgatoList = doc.getElementsByTagName("hallgato");

            System.out.println("----------------------");
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Node node = hallgatoList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    System.out.println((i + 1) + ". " + vezeteknev);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
