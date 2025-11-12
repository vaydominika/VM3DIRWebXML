package hu.domparse.vm3dir;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class VM3DIRDOMModify {
    
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("VM3DIR_XMLTask/VM3DIR_XML.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("========================================\n");
            
            // 1. módosítás: Macska nevének módosítása (m1 macska neve "Foltos" -> "Cirmos")
            System.out.println("1. MÓDOSÍTÁS: Macska nevének módosítása (m1: 'Foltos' -> 'Cirmos')\n");
            NodeList macskaList = doc.getElementsByTagName("macska");
            for (int i = 0; i < macskaList.getLength(); i++) {
                Element macska = (Element) macskaList.item(i);
                if ("m1".equals(macska.getAttribute("macska_id"))) {
                    Node nevNode = macska.getElementsByTagName("nev").item(0);
                    if (nevNode != null) {
                        String regiNev = nevNode.getTextContent();
                        System.out.println("  - Macska m1 neve módosítva lenne: '" + regiNev + "' -> 'Cirmos'");
                    }
                    break;
                }
            }
            
            System.out.println("\n");
            
            // 2. módosítás: Örökbefogadás státuszának módosítása (of2: "Folyamatban" -> "Jóváhagyva")
            System.out.println("2. MÓDOSÍTÁS: Örökbefogadás státuszának módosítása (of2: 'Folyamatban' -> 'Jóváhagyva')\n");
            NodeList orokbefogadasList = doc.getElementsByTagName("orokbefogadas");
            for (int i = 0; i < orokbefogadasList.getLength(); i++) {
                Element orokbefogadas = (Element) orokbefogadasList.item(i);
                if ("of2".equals(orokbefogadas.getAttribute("orokbefogadas_id"))) {
                    Node statuszNode = orokbefogadas.getElementsByTagName("statusz").item(0);
                    if (statuszNode != null) {
                        String regiStatusz = statuszNode.getTextContent();
                        System.out.println("  - Örökbefogadás of2 státusza módosítva lenne: '" + regiStatusz + "' -> 'Jóváhagyva'");
                    }
                    break;
                }
            }
            
            System.out.println("\n");
            
            // 3. módosítás: Állatorvos telefonszámának módosítása (ao1)
            System.out.println("3. MÓDOSÍTÁS: Állatorvos telefonszámának módosítása (ao1)\n");
            NodeList allatorvosList = doc.getElementsByTagName("allatorvos");
            for (int i = 0; i < allatorvosList.getLength(); i++) {
                Element allatorvos = (Element) allatorvosList.item(i);
                if ("ao1".equals(allatorvos.getAttribute("allatorvos_id"))) {
                    Node telefonszamNode = allatorvos.getElementsByTagName("telefonszam").item(0);
                    if (telefonszamNode != null) {
                        String regiTelefon = telefonszamNode.getTextContent();
                        System.out.println("  - Állatorvos ao1 telefonszáma módosítva lenne: '" + regiTelefon + "' -> '+36309999999'");
                    }
                    break;
                }
            }
            
            System.out.println("\n");
            
            // 4. módosítás: Egészségügyi karton diagnózisának módosítása (k2)
            System.out.println("4. MÓDOSÍTÁS: Egészségügyi karton diagnózisának módosítása (k2)\n");
            NodeList kartonList = doc.getElementsByTagName("egeszsegugyi_karton");
            for (int i = 0; i < kartonList.getLength(); i++) {
                Element karton = (Element) kartonList.item(i);
                if ("k2".equals(karton.getAttribute("karton_id"))) {
                    Node diagnozisNode = karton.getElementsByTagName("diagnozis").item(0);
                    if (diagnozisNode != null) {
                        String regiDiagnozis = diagnozisNode.getTextContent();
                        System.out.println("  - Karton k2 diagnózisa módosítva lenne:");
                        System.out.println("    Régi: '" + regiDiagnozis + "'");
                        System.out.println("    Új: 'Betegség, hányás - gyógyult'");
                    }
                    break;
                }
            }
            
            System.out.println("\n");
            
            // 5. módosítás (bónusz): Új szakképzettség hozzáadása állatorvoshoz (ao2)
            System.out.println("5. MÓDOSÍTÁS (BÓNUSZ): Új szakképzettség hozzáadása állatorvoshoz (ao2)\n");
            for (int i = 0; i < allatorvosList.getLength(); i++) {
                Element allatorvos = (Element) allatorvosList.item(i);
                if ("ao2".equals(allatorvos.getAttribute("allatorvos_id"))) {
                    Element szakkepesitesElem = (Element) allatorvos.getElementsByTagName("szakkepesites").item(0);
                    if (szakkepesitesElem != null) {
                        System.out.println("  - Új szakképzettség hozzáadva lenne az állatorvos ao2-hez: 'Kardiológia'");
                    }
                    break;
                }
            }
            
            System.out.println("\n");
            
            // Transformer beállítása XML konzolra való íráshoz
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            
            System.out.println("\n=== XML TARTALOM KONZOLRA ===");
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

