package xpathvm3dir;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModifyOrarendVM3DIR {
    
    public static void main(String[] args) {
        PrintWriter fileWriter = null;
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
            
            System.out.println("=== XPath Módosítások - Orarend VM3DIR ===\n");
            System.out.println("Neptunkod: " + vm3dir + "\n");
            System.out.println("==========================================\n");
            
            // Kimeneti fájl létrehozása
            File outputFile = new File("VM3DIR_1112/xPathModifyOrarendVM3DIR_output.txt");
            fileWriter = new PrintWriter(new FileWriter(outputFile));
            
            // 1. Módosítás: ID="01" óra helyszínének módosítása
            // String xpathExpression1 = "//ora[@id='01']";
            String xpathExpression1 = "//ora[@id='01']";
            NodeList nodeList1 = (NodeList) xpath.evaluate(xpathExpression1, doc, XPathConstants.NODESET);
            if (nodeList1.getLength() > 0) {
                Element ora1 = (Element) nodeList1.item(0);
                Node helyszinNode1 = ora1.getElementsByTagName("helyszin").item(0);
                if (helyszinNode1 != null) {
                    String regiHelyszin1 = helyszinNode1.getTextContent();
                    helyszinNode1.setTextContent("5. előadó (módosítva)");
                    System.out.println("1. MÓDOSÍTÁS: ID='01' óra helyszíne");
                    System.out.println("   Régi helyszín: " + regiHelyszin1);
                    System.out.println("   Új helyszín: " + helyszinNode1.getTextContent());
                    fileWriter.println("1. MÓDOSÍTÁS: ID='01' óra helyszíne");
                    fileWriter.println("   Régi helyszín: " + regiHelyszin1);
                    fileWriter.println("   Új helyszín: " + helyszinNode1.getTextContent());
                    System.out.println();
                    fileWriter.println();
                }
            }
            
            // 2. Módosítás: ID="02" óra időpontjának módosítása
            // String xpathExpression2 = "//ora[@id='02']";
            String xpathExpression2 = "//ora[@id='02']";
            NodeList nodeList2 = (NodeList) xpath.evaluate(xpathExpression2, doc, XPathConstants.NODESET);
            if (nodeList2.getLength() > 0) {
                Element ora2 = (Element) nodeList2.item(0);
                Node tolNode = ((Element) ora2.getElementsByTagName("idopont").item(0)).getElementsByTagName("tol").item(0);
                Node igNode = ((Element) ora2.getElementsByTagName("idopont").item(0)).getElementsByTagName("ig").item(0);
                if (tolNode != null && igNode != null) {
                    String regiTol = tolNode.getTextContent();
                    String regiIg = igNode.getTextContent();
                    tolNode.setTextContent("11:00");
                    igNode.setTextContent("13:00");
                    System.out.println("2. MÓDOSÍTÁS: ID='02' óra időpontja");
                    System.out.println("   Régi időpont: " + regiTol + "-" + regiIg);
                    System.out.println("   Új időpont: " + tolNode.getTextContent() + "-" + igNode.getTextContent());
                    fileWriter.println("2. MÓDOSÍTÁS: ID='02' óra időpontja");
                    fileWriter.println("   Régi időpont: " + regiTol + "-" + regiIg);
                    fileWriter.println("   Új időpont: " + tolNode.getTextContent() + "-" + igNode.getTextContent());
                    System.out.println();
                    fileWriter.println();
                }
            }
            
            // 3. Módosítás: ID="03" óra oktatójának módosítása
            // String xpathExpression3 = "//ora[@id='03']";
            String xpathExpression3 = "//ora[@id='03']";
            NodeList nodeList3 = (NodeList) xpath.evaluate(xpathExpression3, doc, XPathConstants.NODESET);
            if (nodeList3.getLength() > 0) {
                Element ora3 = (Element) nodeList3.item(0);
                Node oktatoNode = ora3.getElementsByTagName("oktato").item(0);
                if (oktatoNode != null) {
                    String regiOktato = oktatoNode.getTextContent();
                    oktatoNode.setTextContent("Dr. Kovács Péter");
                    System.out.println("3. MÓDOSÍTÁS: ID='03' óra oktatója");
                    System.out.println("   Régi oktató: " + regiOktato);
                    System.out.println("   Új oktató: " + oktatoNode.getTextContent());
                    fileWriter.println("3. MÓDOSÍTÁS: ID='03' óra oktatója");
                    fileWriter.println("   Régi oktató: " + regiOktato);
                    fileWriter.println("   Új oktató: " + oktatoNode.getTextContent());
                    System.out.println();
                    fileWriter.println();
                }
            }
            
            // Módosított XML mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("VM3DIR_1112/orarendVM3DIR1.xml"));
            transformer.transform(source, result);
            
            System.out.println("Módosított XML mentve: orarendVM3DIR1.xml\n");
            fileWriter.println("Módosított XML mentve: orarendVM3DIR1.xml\n");
            
            // Módosított órák strukturált kiírása
            System.out.println("=== MÓDOSÍTOTT ÓRÁK STRUKTURÁLT FORMÁBAN ===\n");
            fileWriter.println("=== MÓDOSÍTOTT ÓRÁK STRUKTURÁLT FORMÁBAN ===\n");
            
            // Módosított órák lekérdezése
            String xpathExpressionModified = "//ora[@id='01' or @id='02' or @id='03']";
            NodeList modifiedOrak = (NodeList) xpath.evaluate(xpathExpressionModified, doc, XPathConstants.NODESET);
            
            for (int i = 0; i < modifiedOrak.getLength(); i++) {
                org.w3c.dom.Node ora = modifiedOrak.item(i);
                String id = ((Element) ora).getAttribute("id");
                String tipus = ((Element) ora).getAttribute("tipus");
                String targy = xpath.evaluate("targy", ora);
                String nap = xpath.evaluate("idopont/nap", ora);
                String tol = xpath.evaluate("idopont/tol", ora);
                String ig = xpath.evaluate("idopont/ig", ora);
                String helyszin = xpath.evaluate("helyszin", ora);
                String oktato = xpath.evaluate("oktato", ora);
                String szak = xpath.evaluate("szak", ora);
                
                System.out.println("Óra ID: " + id);
                System.out.println("  Típus: " + tipus);
                System.out.println("  Tárgy: " + targy);
                System.out.println("  Időpont: " + nap + ", " + tol + "-" + ig);
                System.out.println("  Helyszín: " + helyszin);
                System.out.println("  Oktató: " + oktato);
                System.out.println("  Szak: " + szak);
                System.out.println("  ---");
                
                fileWriter.println("Óra ID: " + id);
                fileWriter.println("  Típus: " + tipus);
                fileWriter.println("  Tárgy: " + targy);
                fileWriter.println("  Időpont: " + nap + ", " + tol + "-" + ig);
                fileWriter.println("  Helyszín: " + helyszin);
                fileWriter.println("  Oktató: " + oktato);
                fileWriter.println("  Szak: " + szak);
                fileWriter.println("  ---");
            }
            
            // Konzolra XML tartalom kiírása
            System.out.println("\n=== MÓDOSÍTOTT XML TARTALOM ===\n");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (fileWriter != null) {
                fileWriter.println("Hiba történt: " + e.getMessage());
            }
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}

