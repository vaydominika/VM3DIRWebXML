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

public class xPathVM3DIR {
    
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
            
            System.out.println("=== XPath Lekérdezések - Student VM3DIR ===\n");
            System.out.println("Neptunkod: " + vm3dir + "\n");
            System.out.println("==========================================\n");
            
            // 1. Válassza ki az összes student element, amely a class gyermekei!
            // String xpathExpression1 = "class/student";
            String xpathExpression1 = "class/student";
            NodeList nodeList1 = (NodeList) xpath.evaluate(xpathExpression1, doc, XPathConstants.NODESET);
            System.out.println("1. LEKÉRDEZÉS: Összes student element, amely a class gyermekei");
            for (int i = 0; i < nodeList1.getLength(); i++) {
                Element student = (Element) nodeList1.item(i);
                String id = student.getAttribute("id");
                System.out.println("   - Student ID: " + id);
            }
            System.out.println();
            
            // 2. Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
            // String xpathExpression2 = "//student[@id='02']";
            String xpathExpression2 = "//student[@id='02']";
            NodeList nodeList2 = (NodeList) xpath.evaluate(xpathExpression2, doc, XPathConstants.NODESET);
            System.out.println("2. LEKÉRDEZÉS: Student element id='02' attribútummal");
            if (nodeList2.getLength() > 0) {
                Element student = (Element) nodeList2.item(0);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev);
            }
            System.out.println();
            
            // 3. Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
            // String xpathExpression3 = "//student";
            String xpathExpression3 = "//student";
            NodeList nodeList3 = (NodeList) xpath.evaluate(xpathExpression3, doc, XPathConstants.NODESET);
            System.out.println("3. LEKÉRDEZÉS: Összes student element a dokumentumban");
            for (int i = 0; i < nodeList3.getLength(); i++) {
                Element student = (Element) nodeList3.item(i);
                String id = student.getAttribute("id");
                System.out.println("   - Student ID: " + id);
            }
            System.out.println();
            
            // 4. Válassza ki a második student element, amely a class root element gyermeke!
            // String xpathExpression4 = "/class/student[2]";
            String xpathExpression4 = "/class/student[2]";
            NodeList nodeList4 = (NodeList) xpath.evaluate(xpathExpression4, doc, XPathConstants.NODESET);
            System.out.println("4. LEKÉRDEZÉS: Második student element a class root element gyermeke");
            if (nodeList4.getLength() > 0) {
                Element student = (Element) nodeList4.item(0);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev);
            }
            System.out.println();
            
            // 5. Válassza ki az utolsó student elemet, amely a class root element gyermeke!
            // String xpathExpression5 = "/class/student[last()]";
            String xpathExpression5 = "/class/student[last()]";
            NodeList nodeList5 = (NodeList) xpath.evaluate(xpathExpression5, doc, XPathConstants.NODESET);
            System.out.println("5. LEKÉRDEZÉS: Utolsó student element a class root element gyermeke");
            if (nodeList5.getLength() > 0) {
                Element student = (Element) nodeList5.item(0);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev);
            }
            System.out.println();
            
            // 6. Válassza ki a utolsó előtti student elemet, amely a class root element gyermeke!
            // String xpathExpression6 = "/class/student[last()-1]";
            String xpathExpression6 = "/class/student[last()-1]";
            NodeList nodeList6 = (NodeList) xpath.evaluate(xpathExpression6, doc, XPathConstants.NODESET);
            System.out.println("6. LEKÉRDEZÉS: Utolsó előtti student element a class root element gyermeke");
            if (nodeList6.getLength() > 0) {
                Element student = (Element) nodeList6.item(0);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev);
            }
            System.out.println();
            
            // 7. Válassza ki az első két student elemet, amelyek a root element gyermekei!
            // String xpathExpression7 = "/class/student[position() <= 2]";
            String xpathExpression7 = "/class/student[position() <= 2]";
            NodeList nodeList7 = (NodeList) xpath.evaluate(xpathExpression7, doc, XPathConstants.NODESET);
            System.out.println("7. LEKÉRDEZÉS: Első két student element a root element gyermekei");
            for (int i = 0; i < nodeList7.getLength(); i++) {
                Element student = (Element) nodeList7.item(i);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev);
            }
            System.out.println();
            
            // 8. Válassza ki class root element összes gyermek elemét!
            // String xpathExpression8 = "/class/*";
            String xpathExpression8 = "/class/*";
            NodeList nodeList8 = (NodeList) xpath.evaluate(xpathExpression8, doc, XPathConstants.NODESET);
            System.out.println("8. LEKÉRDEZÉS: Class root element összes gyermek eleme");
            for (int i = 0; i < nodeList8.getLength(); i++) {
                Node node = nodeList8.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("   - Element: " + element.getNodeName() + " (ID: " + element.getAttribute("id") + ")");
                }
            }
            System.out.println();
            
            // 9. Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
            // String xpathExpression9 = "//student[@*]";
            String xpathExpression9 = "//student[@*]";
            NodeList nodeList9 = (NodeList) xpath.evaluate(xpathExpression9, doc, XPathConstants.NODESET);
            System.out.println("9. LEKÉRDEZÉS: Összes student element legalább egy attribútummal");
            for (int i = 0; i < nodeList9.getLength(); i++) {
                Element student = (Element) nodeList9.item(i);
                String id = student.getAttribute("id");
                System.out.println("   - Student ID: " + id);
            }
            System.out.println();
            
            // 10. Válassza ki a dokumentum összes elemét!
            // String xpathExpression10 = "//*";
            String xpathExpression10 = "//*";
            NodeList nodeList10 = (NodeList) xpath.evaluate(xpathExpression10, doc, XPathConstants.NODESET);
            System.out.println("10. LEKÉRDEZÉS: Dokumentum összes eleme");
            for (int i = 0; i < nodeList10.getLength(); i++) {
                Node node = nodeList10.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nodeName = element.getNodeName();
                    String id = element.hasAttribute("id") ? " (ID: " + element.getAttribute("id") + ")" : "";
                    System.out.println("   - Element: " + nodeName + id);
                }
            }
            System.out.println();
            
            // 11. Válassza ki a class root element összes student elemét, amelynél a kor>20!
            // String xpathExpression11 = "/class/student[kor > 20]";
            String xpathExpression11 = "/class/student[kor > 20]";
            NodeList nodeList11 = (NodeList) xpath.evaluate(xpathExpression11, doc, XPathConstants.NODESET);
            System.out.println("11. LEKÉRDEZÉS: Class root element összes student eleme, ahol kor>20");
            for (int i = 0; i < nodeList11.getLength(); i++) {
                Element student = (Element) nodeList11.item(i);
                String id = student.getAttribute("id");
                String keresztnev = xpath.evaluate("keresztnev", student);
                String vezeteknev = xpath.evaluate("vezeteknev", student);
                String kor = xpath.evaluate("kor", student);
                System.out.println("   - ID: " + id + ", Név: " + vezeteknev + " " + keresztnev + ", Kor: " + kor);
            }
            System.out.println();
            
            // 12. Válassza ki az összes student elem összes keresztnev or vezeteknev csomópontot!
            // String xpathExpression12 = "//student/keresztnev | //student/vezeteknev";
            String xpathExpression12 = "//student/keresztnev | //student/vezeteknev";
            NodeList nodeList12 = (NodeList) xpath.evaluate(xpathExpression12, doc, XPathConstants.NODESET);
            System.out.println("12. LEKÉRDEZÉS: Összes student elem összes keresztnev vagy vezeteknev csomópont");
            for (int i = 0; i < nodeList12.getLength(); i++) {
                Node node = nodeList12.item(i);
                String nodeName = node.getNodeName();
                String value = node.getTextContent();
                System.out.println("   - " + nodeName + ": " + value);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
