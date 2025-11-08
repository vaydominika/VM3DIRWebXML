package hu.domparse.vm3dir;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VM3DIRDOMRead {
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        
        // XML fájl beolvasása
        File xmlFile = new File("VM3DIR_XMLTask/VM3DIR_XML.xml");
        
        // DocumentBuilderFactory létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        
        // DocumentBuilder létrehozása
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        // XML dokumentum feldolgozása
        Document doc = dBuilder.parse(xmlFile);
        
        // Dokumentum normalizálása
        doc.getDocumentElement().normalize();
        
        // Gyökér elem kiírása
        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("========================================\n");
        
        // Egészségügyi kartonok feldolgozása
        System.out.println("=== EGÉSZSÉGÜGYI KARTONOK ===\n");
        NodeList kartonList = doc.getElementsByTagName("egeszsegugyi_karton");
        for (int i = 0; i < kartonList.getLength(); i++) {
            Node nNode = kartonList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Karton ID: " + elem.getAttribute("karton_id"));
                System.out.println("Macska ref: " + elem.getAttribute("macska_ref"));
                System.out.println("Állatorvos ref: " + elem.getAttribute("allatorvos_ref"));
                
                Node datumNode = elem.getElementsByTagName("datum").item(0);
                if (datumNode != null) {
                    System.out.println("Dátum: " + datumNode.getTextContent());
                }
                
                Node kezelesNode = elem.getElementsByTagName("kezeles_tipusa").item(0);
                if (kezelesNode != null) {
                    System.out.println("Kezelés típusa: " + kezelesNode.getTextContent());
                }
                
                Node diagnozisNode = elem.getElementsByTagName("diagnozis").item(0);
                if (diagnozisNode != null) {
                    System.out.println("Diagnózis: " + diagnozisNode.getTextContent());
                }
                
                Node gyogyszerekNode = elem.getElementsByTagName("gyogyszerek").item(0);
                if (gyogyszerekNode != null) {
                    System.out.println("Gyógyszerek: " + gyogyszerekNode.getTextContent());
                }
                
                Node kovetkezoNode = elem.getElementsByTagName("kovetkezo_vizsgalat").item(0);
                if (kovetkezoNode != null) {
                    System.out.println("Következő vizsgálat: " + kovetkezoNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Örökbefogadások feldolgozása
        System.out.println("=== ÖRÖKBEFOGADÁSOK ===\n");
        NodeList orokbefogadasList = doc.getElementsByTagName("orokbefogadas");
        for (int i = 0; i < orokbefogadasList.getLength(); i++) {
            Node nNode = orokbefogadasList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Örökbefogadás ID: " + elem.getAttribute("orokbefogadas_id"));
                System.out.println("Macska ref: " + elem.getAttribute("macska_ref"));
                System.out.println("Örökbefogadó ref: " + elem.getAttribute("orokbefogado_ref"));
                
                Node datumNode = elem.getElementsByTagName("orokbefogadas_datuma").item(0);
                if (datumNode != null) {
                    System.out.println("Örökbefogadás dátuma: " + datumNode.getTextContent());
                }
                
                Node dijNode = elem.getElementsByTagName("orokbefogasi_dij").item(0);
                if (dijNode != null) {
                    System.out.println("Örökbefogadási díj: " + dijNode.getTextContent());
                }
                
                Node statuszNode = elem.getElementsByTagName("statusz").item(0);
                if (statuszNode != null) {
                    System.out.println("Státusz: " + statuszNode.getTextContent());
                }
                
                Node megjegyzesekNode = elem.getElementsByTagName("megjegyzesek").item(0);
                if (megjegyzesekNode != null) {
                    System.out.println("Megjegyzések: " + megjegyzesekNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Örökbefogadók feldolgozása
        System.out.println("=== ÖRÖKBEFOGADÓK ===\n");
        NodeList orokbefogadoList = doc.getElementsByTagName("orokbefogado");
        for (int i = 0; i < orokbefogadoList.getLength(); i++) {
            Node nNode = orokbefogadoList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Örökbefogadó ID: " + elem.getAttribute("orokbefogado_id"));
                
                Element teljesNevElem = (Element) elem.getElementsByTagName("teljes_nev").item(0);
                if (teljesNevElem != null) {
                    Node vezeteknevNode = teljesNevElem.getElementsByTagName("vezeteknev").item(0);
                    Node keresztnevNode = teljesNevElem.getElementsByTagName("keresztnev").item(0);
                    if (vezeteknevNode != null && keresztnevNode != null) {
                        System.out.println("Név: " + vezeteknevNode.getTextContent() + " " + keresztnevNode.getTextContent());
                    }
                }
                
                Element cimElem = (Element) elem.getElementsByTagName("cim").item(0);
                if (cimElem != null) {
                    Node iranyitoszamNode = cimElem.getElementsByTagName("iranyitoszam").item(0);
                    Node varosNode = cimElem.getElementsByTagName("varos").item(0);
                    Node utcaNode = cimElem.getElementsByTagName("utca").item(0);
                    Node hazszamNode = cimElem.getElementsByTagName("hazszam").item(0);
                    if (iranyitoszamNode != null && varosNode != null && utcaNode != null && hazszamNode != null) {
                        System.out.println("Cím: " + iranyitoszamNode.getTextContent() + " " + 
                                          varosNode.getTextContent() + ", " + 
                                          utcaNode.getTextContent() + " " + 
                                          hazszamNode.getTextContent());
                    }
                }
                
                Node telefonszamNode = elem.getElementsByTagName("telefonszam").item(0);
                if (telefonszamNode != null) {
                    System.out.println("Telefonszám: " + telefonszamNode.getTextContent());
                }
                
                Node emailNode = elem.getElementsByTagName("email").item(0);
                if (emailNode != null) {
                    System.out.println("Email: " + emailNode.getTextContent());
                }
                
                Node szuletesDatumNode = elem.getElementsByTagName("szuletes_datum").item(0);
                if (szuletesDatumNode != null) {
                    System.out.println("Születési dátum: " + szuletesDatumNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Macskák feldolgozása
        System.out.println("=== MACSKÁK ===\n");
        NodeList macskaList = doc.getElementsByTagName("macska");
        for (int i = 0; i < macskaList.getLength(); i++) {
            Node nNode = macskaList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Macska ID: " + elem.getAttribute("macska_id"));
                System.out.println("Alkalmazott ref: " + elem.getAttribute("alkalmazott_ref"));
                
                Node nevNode = elem.getElementsByTagName("nev").item(0);
                if (nevNode != null) {
                    System.out.println("Név: " + nevNode.getTextContent());
                }
                
                Node fajtaNode = elem.getElementsByTagName("fajta").item(0);
                if (fajtaNode != null) {
                    System.out.println("Fajta: " + fajtaNode.getTextContent());
                }
                
                Node szuletesDatumNode = elem.getElementsByTagName("szuletes_datum").item(0);
                if (szuletesDatumNode != null) {
                    System.out.println("Születési dátum: " + szuletesDatumNode.getTextContent());
                }
                
                Node szinNode = elem.getElementsByTagName("szin").item(0);
                if (szinNode != null) {
                    System.out.println("Szín: " + szinNode.getTextContent());
                }
                
                Node nemNode = elem.getElementsByTagName("nem").item(0);
                if (nemNode != null) {
                    System.out.println("Nem: " + nemNode.getTextContent());
                }
                
                Node bekerulesiDatumNode = elem.getElementsByTagName("bekerulesi_datum").item(0);
                if (bekerulesiDatumNode != null) {
                    System.out.println("Bekerülési dátum: " + bekerulesiDatumNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Alkalmazottak feldolgozása
        System.out.println("=== ALKALMAZOTTAK ===\n");
        NodeList alkalmazottList = doc.getElementsByTagName("alkalmazott");
        for (int i = 0; i < alkalmazottList.getLength(); i++) {
            Node nNode = alkalmazottList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Alkalmazott ID: " + elem.getAttribute("alkalmazott_id"));
                
                Element teljesNevElem = (Element) elem.getElementsByTagName("teljes_nev").item(0);
                if (teljesNevElem != null) {
                    Node vezeteknevNode = teljesNevElem.getElementsByTagName("vezeteknev").item(0);
                    Node keresztnevNode = teljesNevElem.getElementsByTagName("keresztnev").item(0);
                    if (vezeteknevNode != null && keresztnevNode != null) {
                        System.out.println("Név: " + vezeteknevNode.getTextContent() + " " + keresztnevNode.getTextContent());
                    }
                }
                
                Element cimElem = (Element) elem.getElementsByTagName("cim").item(0);
                if (cimElem != null) {
                    Node iranyitoszamNode = cimElem.getElementsByTagName("iranyitoszam").item(0);
                    Node varosNode = cimElem.getElementsByTagName("varos").item(0);
                    Node utcaNode = cimElem.getElementsByTagName("utca").item(0);
                    Node hazszamNode = cimElem.getElementsByTagName("hazszam").item(0);
                    if (iranyitoszamNode != null && varosNode != null && utcaNode != null && hazszamNode != null) {
                        System.out.println("Cím: " + iranyitoszamNode.getTextContent() + " " + 
                                          varosNode.getTextContent() + ", " + 
                                          utcaNode.getTextContent() + " " + 
                                          hazszamNode.getTextContent());
                    }
                }
                
                Node telefonszamNode = elem.getElementsByTagName("telefonszam").item(0);
                if (telefonszamNode != null) {
                    System.out.println("Telefonszám: " + telefonszamNode.getTextContent());
                }
                
                Node beosztasNode = elem.getElementsByTagName("beosztas").item(0);
                if (beosztasNode != null) {
                    System.out.println("Beosztás: " + beosztasNode.getTextContent());
                }
                
                Node belepesDatumNode = elem.getElementsByTagName("belepes_datuma").item(0);
                if (belepesDatumNode != null) {
                    System.out.println("Belépés dátuma: " + belepesDatumNode.getTextContent());
                }
                
                Node fizetesNode = elem.getElementsByTagName("fizetes").item(0);
                if (fizetesNode != null) {
                    System.out.println("Fizetés: " + fizetesNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Állatorvosok feldolgozása
        System.out.println("=== ÁLLATORVOSOK ===\n");
        NodeList allatorvosList = doc.getElementsByTagName("allatorvos");
        for (int i = 0; i < allatorvosList.getLength(); i++) {
            Node nNode = allatorvosList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                System.out.println("Állatorvos ID: " + elem.getAttribute("allatorvos_id"));
                
                Element teljesNevElem = (Element) elem.getElementsByTagName("teljes_nev").item(0);
                if (teljesNevElem != null) {
                    Node vezeteknevNode = teljesNevElem.getElementsByTagName("vezeteknev").item(0);
                    Node keresztnevNode = teljesNevElem.getElementsByTagName("keresztnev").item(0);
                    if (vezeteknevNode != null && keresztnevNode != null) {
                        System.out.println("Név: " + vezeteknevNode.getTextContent() + " " + keresztnevNode.getTextContent());
                    }
                }
                
                Element szakkepesitesElem = (Element) elem.getElementsByTagName("szakkepesites").item(0);
                if (szakkepesitesElem != null) {
                    NodeList szakkepesitesList = szakkepesitesElem.getElementsByTagName("szakkepesites");
                    System.out.print("Szakképzettségek: ");
                    for (int j = 0; j < szakkepesitesList.getLength(); j++) {
                        if (j > 0) System.out.print(", ");
                        System.out.print(szakkepesitesList.item(j).getTextContent());
                    }
                    System.out.println();
                }
                
                Node rendeloNeveNode = elem.getElementsByTagName("rendelo_neve").item(0);
                if (rendeloNeveNode != null) {
                    System.out.println("Rendelő neve: " + rendeloNeveNode.getTextContent());
                }
                
                Node telefonszamNode = elem.getElementsByTagName("telefonszam").item(0);
                if (telefonszamNode != null) {
                    System.out.println("Telefonszám: " + telefonszamNode.getTextContent());
                }
                
                Node emailNode = elem.getElementsByTagName("email").item(0);
                if (emailNode != null) {
                    System.out.println("Email: " + emailNode.getTextContent());
                }
                System.out.println("---\n");
            }
        }
        
        // Transformer beállítása XML konzolra való íráshoz
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // DOMSource létrehozása
        DOMSource source = new DOMSource(doc);
        
        // Konzolra írás
        System.out.println("\n\n=== XML TARTALOM KONZOLRA (BLOKK FORMÁTUM) ===\n");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}



