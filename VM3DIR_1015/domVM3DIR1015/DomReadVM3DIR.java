package domVM3DIR1015;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;

public class DomReadVM3DIR {
    
    private static int indentLevel = 0;
    
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            File xmlFile = new File("../../VM3DIR_1008/VM3DIR_orarend.xml");
            System.out.println("Looking for XML file at: " + xmlFile.getAbsolutePath());
            System.out.println("File exists: " + xmlFile.exists());
            
            if (!xmlFile.exists()) {
                System.err.println("XML file not found! Please check the path.");
                return;
            }
            
            Document document = builder.parse(xmlFile);
            
            Element root = document.getDocumentElement();
            
            System.out.println("=== DOM Parser - XML Tree Structure ===");
            System.out.println("File: VM3DIR_1008/VM3DIR_orarend.xml");
            System.out.println("=====================================");
            
            displayNode(root);
            
        } catch (Exception e) {
            System.err.println("Error parsing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void displayNode(Node node) {
        if (node == null) return;
        
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                displayElement((Element) node);
                break;
            case Node.TEXT_NODE:
                displayText((Text) node);
                break;
            case Node.ATTRIBUTE_NODE:
                displayAttribute((Attr) node);
                break;
            default:
                printIndent();
                System.out.println("[" + node.getNodeType() + "] " + node.getNodeName());
        }
    }
    
    private static void displayElement(Element element) {
        printIndent();
        System.out.print("<" + element.getNodeName());
        
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                System.out.print(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
            }
        }
        
        System.out.println(">");
        
        NodeList children = element.getChildNodes();
        if (children.getLength() > 0) {
            indentLevel++;
            for (int i = 0; i < children.getLength(); i++) {
                displayNode(children.item(i));
            }
            indentLevel--;
        }
        
        printIndent();
        System.out.println("</" + element.getNodeName() + ">");
    }
    
    private static void displayText(Text text) {
        String content = text.getTextContent().trim();
        if (!content.isEmpty()) {
            printIndent();
            System.out.println("[TEXT] " + content);
        }
    }
 
    private static void displayAttribute(Attr attr) {
        printIndent();
        System.out.println("[ATTR] " + attr.getName() + " = \"" + attr.getValue() + "\"");
    }
    
    private static void printIndent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }
}
