package domvm3dir1029;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

class DomParseVM3DIR {

    public static void main(String[] args) {
        try {
            File inputFile = new File("domvm3dir1029/VM3DIR_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList oraList = doc.getElementsByTagName("ora");
            if (oraList.getLength() > 0) {
                Element elsoOra = (Element) oraList.item(0);

                Element oraado = doc.createElement("oraado");
                oraado.appendChild(doc.createTextNode("Dr. Kovács Péter"));
                elsoOra.appendChild(oraado);

                System.out.println("Hozzáadva az első órához: <oraado>Dr. Kovács Péter</oraado>");
            }

            for (int i = 0; i < oraList.getLength(); i++) {
                Element oraElem = (Element) oraList.item(i);
                String tipus = oraElem.getAttribute("tipus");

                if (tipus.equalsIgnoreCase("gyakorlat")) {
                    oraElem.setAttribute("tipus", "eloadas");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);

            System.out.println("\n=== Módosított XML tartalom ===");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            StreamResult fileResult = new StreamResult(new File("domvm3dir1029/orarendModify1VM3DIR.xml"));
            transformer.transform(source, fileResult);

            System.out.println("\n\nFájl elmentve: domvm3dir1029/orarendModify1VM3DIR.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}