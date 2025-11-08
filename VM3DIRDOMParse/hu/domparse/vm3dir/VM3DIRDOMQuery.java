package hu.domparse.vm3dir;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class VM3DIRDOMQuery {
    
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("VM3DIR_XMLTask/VM3DIR_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("========================================\n");
            
            // 1. lekérdezés: Összes macska neve és fajtája
            System.out.println("1. LEKÉRDEZÉS: Összes macska neve és fajtája\n");
            NodeList macskaList = doc.getElementsByTagName("macska");
            for (int i = 0; i < macskaList.getLength(); i++) {
                Element macska = (Element) macskaList.item(i);
                String macskaId = macska.getAttribute("macska_id");
                Node nevNode = macska.getElementsByTagName("nev").item(0);
                Node fajtaNode = macska.getElementsByTagName("fajta").item(0);
                
                if (nevNode != null && fajtaNode != null) {
                    System.out.println("  - " + nevNode.getTextContent() + " (" + fajtaNode.getTextContent() + ") [ID: " + macskaId + "]");
                }
            }
            
            System.out.println("\n");
            
            // 2. lekérdezés: Befejezett státuszú örökbefogadások
            System.out.println("2. LEKÉRDEZÉS: Befejezett státuszú örökbefogadások\n");
            NodeList orokbefogadasList = doc.getElementsByTagName("orokbefogadas");
            int befejezettCount = 0;
            for (int i = 0; i < orokbefogadasList.getLength(); i++) {
                Element orokbefogadas = (Element) orokbefogadasList.item(i);
                Node statuszNode = orokbefogadas.getElementsByTagName("statusz").item(0);
                
                if (statuszNode != null && "Befejezett".equals(statuszNode.getTextContent())) {
                    befejezettCount++;
                    String orokbefogadasId = orokbefogadas.getAttribute("orokbefogadas_id");
                    String macskaRef = orokbefogadas.getAttribute("macska_ref");
                    Node datumNode = orokbefogadas.getElementsByTagName("orokbefogadas_datuma").item(0);
                    Node dijNode = orokbefogadas.getElementsByTagName("orokbefogasi_dij").item(0);
                    
                    System.out.println("  - Örökbefogadás ID: " + orokbefogadasId);
                    System.out.println("    Macska ref: " + macskaRef);
                    if (datumNode != null) {
                        System.out.println("    Dátum: " + datumNode.getTextContent());
                    }
                    if (dijNode != null) {
                        System.out.println("    Díj: " + dijNode.getTextContent() + " Ft");
                    }
                    System.out.println();
                }
            }
            if (befejezettCount == 0) {
                System.out.println("  Nincs befejezett örökbefogadás.");
            }
            
            System.out.println("\n");
            
            // 3. lekérdezés: Összes állatorvos neve és szakképzettségei
            System.out.println("3. LEKÉRDEZÉS: Összes állatorvos neve és szakképzettségei\n");
            NodeList allatorvosList = doc.getElementsByTagName("allatorvos");
            for (int i = 0; i < allatorvosList.getLength(); i++) {
                Element allatorvos = (Element) allatorvosList.item(i);
                String allatorvosId = allatorvos.getAttribute("allatorvos_id");
                
                // Név lekérdezése
                Element teljesNevElem = (Element) allatorvos.getElementsByTagName("teljes_nev").item(0);
                String nev = "";
                if (teljesNevElem != null) {
                    Node vezeteknevNode = teljesNevElem.getElementsByTagName("vezeteknev").item(0);
                    Node keresztnevNode = teljesNevElem.getElementsByTagName("keresztnev").item(0);
                    if (vezeteknevNode != null && keresztnevNode != null) {
                        nev = vezeteknevNode.getTextContent() + " " + keresztnevNode.getTextContent();
                    }
                }
                
                // Szakképzettségek lekérdezése
                Element szakkepesitesElem = (Element) allatorvos.getElementsByTagName("szakkepesites").item(0);
                System.out.println("  - " + nev + " [ID: " + allatorvosId + "]");
                if (szakkepesitesElem != null) {
                    NodeList szakkepesitesList = szakkepesitesElem.getElementsByTagName("szakkepesites");
                    for (int j = 0; j < szakkepesitesList.getLength(); j++) {
                        System.out.println("    • " + szakkepesitesList.item(j).getTextContent());
                    }
                }
                System.out.println();
            }
            
            System.out.println("\n");
            
            // 4. lekérdezés: Macskák, amelyeknek van "Betegség" szót tartalmazó diagnózis
            System.out.println("4. LEKÉRDEZÉS: Macskák, amelyeknek van 'Betegség' szót tartalmazó diagnózis\n");
            NodeList kartonList = doc.getElementsByTagName("egeszsegugyi_karton");
            for (int i = 0; i < kartonList.getLength(); i++) {
                Element karton = (Element) kartonList.item(i);
                Node diagnozisNode = karton.getElementsByTagName("diagnozis").item(0);
                
                if (diagnozisNode != null) {
                    String diagnozis = diagnozisNode.getTextContent();
                    // Diagnózis ellenőrzése "Betegség" szóra
                    if (diagnozis.contains("Betegség")) {
                        String macskaRef = karton.getAttribute("macska_ref");
                        String kartonId = karton.getAttribute("karton_id");
                        
                        // Macska nevének megkeresése
                        String macskaNev = "";
                        NodeList macskaList2 = doc.getElementsByTagName("macska");
                        for (int j = 0; j < macskaList2.getLength(); j++) {
                            Element macska = (Element) macskaList2.item(j);
                            if (macskaRef.equals(macska.getAttribute("macska_id"))) {
                                Node nevNode = macska.getElementsByTagName("nev").item(0);
                                if (nevNode != null) {
                                    macskaNev = nevNode.getTextContent();
                                }
                                break;
                            }
                        }
                        
                        System.out.println("  - Macska: " + macskaNev + " [Ref: " + macskaRef + "]");
                        System.out.println("    Karton ID: " + kartonId);
                        System.out.println("    Diagnózis: " + diagnozis);
                        
                        Node datumNode = karton.getElementsByTagName("datum").item(0);
                        if (datumNode != null) {
                            System.out.println("    Dátum: " + datumNode.getTextContent());
                        }
                        System.out.println();
                    }
                }
            }
            
            System.out.println("\n");
            
            // 5. lekérdezés (bónusz): Örökbefogadók, akik Miskolcon laknak
            System.out.println("5. LEKÉRDEZÉS (BÓNUSZ): Örökbefogadók, akik Miskolcon laknak\n");
            NodeList orokbefogadoList = doc.getElementsByTagName("orokbefogado");
            for (int i = 0; i < orokbefogadoList.getLength(); i++) {
                Element orokbefogado = (Element) orokbefogadoList.item(i);
                Element cimElem = (Element) orokbefogado.getElementsByTagName("cim").item(0);
                
                if (cimElem != null) {
                    Node varosNode = cimElem.getElementsByTagName("varos").item(0);
                    if (varosNode != null && "Miskolc".equals(varosNode.getTextContent())) {
                        String orokbefogadoId = orokbefogado.getAttribute("orokbefogado_id");
                        
                        Element teljesNevElem = (Element) orokbefogado.getElementsByTagName("teljes_nev").item(0);
                        String nev = "";
                        if (teljesNevElem != null) {
                            Node vezeteknevNode = teljesNevElem.getElementsByTagName("vezeteknev").item(0);
                            Node keresztnevNode = teljesNevElem.getElementsByTagName("keresztnev").item(0);
                            if (vezeteknevNode != null && keresztnevNode != null) {
                                nev = vezeteknevNode.getTextContent() + " " + keresztnevNode.getTextContent();
                            }
                        }
                        
                        System.out.println("  - " + nev + " [ID: " + orokbefogadoId + "]");
                        
                        Node iranyitoszamNode = cimElem.getElementsByTagName("iranyitoszam").item(0);
                        Node utcaNode = cimElem.getElementsByTagName("utca").item(0);
                        Node hazszamNode = cimElem.getElementsByTagName("hazszam").item(0);
                        if (iranyitoszamNode != null && utcaNode != null && hazszamNode != null) {
                            System.out.println("    Cím: " + iranyitoszamNode.getTextContent() + " " + 
                                              varosNode.getTextContent() + ", " + 
                                              utcaNode.getTextContent() + " " + 
                                              hazszamNode.getTextContent());
                        }
                        System.out.println();
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



