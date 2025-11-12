package xpathvm3dir;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModifyVM3DIR {
    
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File xmlFile = new File("VM3DIR_1112/studentVM3DIR.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            // XPath objektum létrehozása
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            // Változó neve: vm3dir
            String vm3dir = "VM3DIR";
            
            System.out.println("=== XPath Módosítás - Student VM3DIR ===\n");
            System.out.println("Neptunkod: " + vm3dir + "\n");
            System.out.println("==========================================\n");
            
            // Keresés az id="01" hallgatóra XPath-szel
            // String xpathExpression = "//student[@id='01']";
            String xpathExpression = "//student[@id='01']";
            NodeList nodeList = (NodeList) xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET);
            
            if (nodeList.getLength() > 0) {
                Element student = (Element) nodeList.item(0);
                
                // Keresztnév módosítása
                Node keresztnevNode = student.getElementsByTagName("keresztnev").item(0);
                if (keresztnevNode != null) {
                    String regiKeresztnev = keresztnevNode.getTextContent();
                    keresztnevNode.setTextContent("Gábor"); // Új keresztnév
                    System.out.println("Módosítás elvégezve!");
                    System.out.println("Régi keresztnév: " + regiKeresztnev);
                    System.out.println("Új keresztnév: " + keresztnevNode.getTextContent());
                    System.out.println();
                }
                
                // Csak a módosított hallgató kiírása
                System.out.println("=== Módosított hallgató adatai (ID='01') ===");
                String id = student.getAttribute("id");
                String keresztnev = student.getElementsByTagName("keresztnev").item(0).getTextContent();
                String vezeteknev = student.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String becenev = student.getElementsByTagName("becenev").item(0).getTextContent();
                String kor = student.getElementsByTagName("kor").item(0).getTextContent();
                
                System.out.println("ID: " + id);
                System.out.println("Keresztnév: " + keresztnev);
                System.out.println("Vezetéknév: " + vezeteknev);
                System.out.println("Becenév: " + becenev);
                System.out.println("Kor: " + kor);
                
            } else {
                System.out.println("Nem található hallgató ID='01' értékkel!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

