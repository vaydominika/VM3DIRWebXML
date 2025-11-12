package xpathvm3dir;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathQueryOrarendVM3DIR {
    
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File xmlFile = new File("VM3DIR_1112/orarendVM3DIR2.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            // XPath objektum létrehozása
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            // Változó neve: vm3dir
            String vm3dir = "VM3DIR";
            
            System.out.println("=== XPath Lekérdezések - Orarend VM3DIR ===\n");
            System.out.println("Neptunkod: " + vm3dir + "\n");
            System.out.println("==========================================\n");
            
            // 1. Lekérdezés: Összes óra tárgya és típusa
            // String xpathExpression1 = "//ora";
            String xpathExpression1 = "//ora";
            NodeList nodeList1 = (NodeList) xpath.evaluate(xpathExpression1, doc, XPathConstants.NODESET);
            System.out.println("1. LEKÉRDEZÉS: Összes óra tárgya és típusa");
            for (int i = 0; i < nodeList1.getLength(); i++) {
                org.w3c.dom.Node ora = nodeList1.item(i);
                String id = ((org.w3c.dom.Element) ora).getAttribute("id");
                String tipus = ((org.w3c.dom.Element) ora).getAttribute("tipus");
                String targy = xpath.evaluate("targy", ora);
                System.out.println("   - ID: " + id + ", Típus: " + tipus + ", Tárgy: " + targy);
            }
            System.out.println();
            
            // 2. Lekérdezés: Hétfői órák
            // String xpathExpression2 = "//ora[idopont/nap='Hétfő']";
            String xpathExpression2 = "//ora[idopont/nap='Hétfő']";
            NodeList nodeList2 = (NodeList) xpath.evaluate(xpathExpression2, doc, XPathConstants.NODESET);
            System.out.println("2. LEKÉRDEZÉS: Hétfői órák");
            for (int i = 0; i < nodeList2.getLength(); i++) {
                org.w3c.dom.Node ora = nodeList2.item(i);
                String id = ((org.w3c.dom.Element) ora).getAttribute("id");
                String targy = xpath.evaluate("targy", ora);
                String nap = xpath.evaluate("idopont/nap", ora);
                String tol = xpath.evaluate("idopont/tol", ora);
                String ig = xpath.evaluate("idopont/ig", ora);
                System.out.println("   - ID: " + id + ", Tárgy: " + targy + ", Időpont: " + nap + " " + tol + "-" + ig);
            }
            System.out.println();
            
            // 3. Lekérdezés: Agárdi Anita órái
            // String xpathExpression3 = "//ora[oktato='Agárdi Anita']";
            String xpathExpression3 = "//ora[oktato='Agárdi Anita']";
            NodeList nodeList3 = (NodeList) xpath.evaluate(xpathExpression3, doc, XPathConstants.NODESET);
            System.out.println("3. LEKÉRDEZÉS: Agárdi Anita órái");
            for (int i = 0; i < nodeList3.getLength(); i++) {
                org.w3c.dom.Node ora = nodeList3.item(i);
                String id = ((org.w3c.dom.Element) ora).getAttribute("id");
                String targy = xpath.evaluate("targy", ora);
                String tipus = ((org.w3c.dom.Element) ora).getAttribute("tipus");
                String oktato = xpath.evaluate("oktato", ora);
                System.out.println("   - ID: " + id + ", Tárgy: " + targy + ", Típus: " + tipus + ", Oktató: " + oktato);
            }
            System.out.println();
            
            // 4. Lekérdezés: Előadás típusú órák
            // String xpathExpression4 = "//ora[@tipus='eloadas']";
            String xpathExpression4 = "//ora[@tipus='eloadas']";
            NodeList nodeList4 = (NodeList) xpath.evaluate(xpathExpression4, doc, XPathConstants.NODESET);
            System.out.println("4. LEKÉRDEZÉS: Előadás típusú órák");
            System.out.println("   Összesen: " + nodeList4.getLength() + " előadás");
            for (int i = 0; i < nodeList4.getLength(); i++) {
                org.w3c.dom.Node ora = nodeList4.item(i);
                String id = ((org.w3c.dom.Element) ora).getAttribute("id");
                String targy = xpath.evaluate("targy", ora);
                System.out.println("   - ID: " + id + ", Tárgy: " + targy);
            }
            System.out.println();
            
            // 5. Lekérdezés: 10:00-tól kezdődő órák
            // String xpathExpression5 = "//ora[idopont/tol='10:00']";
            String xpathExpression5 = "//ora[idopont/tol='10:00']";
            NodeList nodeList5 = (NodeList) xpath.evaluate(xpathExpression5, doc, XPathConstants.NODESET);
            System.out.println("5. LEKÉRDEZÉS: 10:00-tól kezdődő órák");
            for (int i = 0; i < nodeList5.getLength(); i++) {
                org.w3c.dom.Node ora = nodeList5.item(i);
                String id = ((org.w3c.dom.Element) ora).getAttribute("id");
                String targy = xpath.evaluate("targy", ora);
                String nap = xpath.evaluate("idopont/nap", ora);
                String tol = xpath.evaluate("idopont/tol", ora);
                String ig = xpath.evaluate("idopont/ig", ora);
                System.out.println("   - ID: " + id + ", Tárgy: " + targy + ", Időpont: " + nap + " " + tol + "-" + ig);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

