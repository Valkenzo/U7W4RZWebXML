package u7w4rz.domparse.hu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class U7W4RZDomRead {

    //File xmlFile = new File("C:\\Users\\User\\Egyetemi_dolgok\\5_felev\\webes_adatkez\\beadando\\U7W4RZ_XMLTask\\feladat_1\\U7W4RZ_XML.xml");
	public static void main(String[] args) {
        try {
        	File xmlFile = new File("./feladat_1/U7W4RZ_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("====================================================");

            // ===== ORVOSOK =====
            NodeList orvosok = doc.getElementsByTagName("orvos");
            System.out.println("=== ORVOSOK ===");
            for (int i = 0; i < orvosok.getLength(); i++) {
                Element o = (Element) orvosok.item(i);
                System.out.println("Orvos ID: " + o.getAttribute("id"));
                System.out.println("Név: " + o.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Szakterület: " + o.getElementsByTagName("szakterulet").item(0).getTextContent());

                //Element tels = (Element) o.getElementsByTagName("telefonszamok").item(0);
                NodeList telLista = o.getElementsByTagName("telefonszam");
                for (int j = 0; j < telLista.getLength(); j++) {
                    System.out.println("Telefonszám " + (j + 1) + ": " + telLista.item(j).getTextContent());
                }

                System.out.println("Rendelő szoba: " + o.getElementsByTagName("rendeloSzoba").item(0).getTextContent());
                System.out.println();
            }

            // ===== PACIENSEK =====
            NodeList paciensek = doc.getElementsByTagName("paciens");
            System.out.println("=== PACIENSEK ===");
            for (int i = 0; i < paciensek.getLength(); i++) {
                Element p = (Element) paciensek.item(i);
                System.out.println("Páciens ID: " + p.getAttribute("id"));
                System.out.println("Név: " + p.getElementsByTagName("nev").item(0).getTextContent());
                Element cim = (Element) p.getElementsByTagName("cim").item(0);
                System.out.println("Irányítószám: " + cim.getElementsByTagName("iranyitoszam").item(0).getTextContent());
                System.out.println("Város: " + cim.getElementsByTagName("varos").item(0).getTextContent());
                System.out.println("Utca: " + cim.getElementsByTagName("utca").item(0).getTextContent());
                System.out.println("Házszám: " + cim.getElementsByTagName("hazszam").item(0).getTextContent());
                System.out.println("Születési dátum: " + p.getElementsByTagName("szuletesiDatum").item(0).getTextContent());
                System.out.println("TAJ: " + p.getElementsByTagName("taj").item(0).getTextContent());
                System.out.println();
            }

            // ===== KARTONOK =====
            NodeList kartonok = doc.getElementsByTagName("karton");
            System.out.println("=== KARTONOK ===");
            for (int i = 0; i < kartonok.getLength(); i++) {
                Element k = (Element) kartonok.item(i);
                System.out.println("Karton ID: " + k.getAttribute("id"));
                System.out.println("Páciens ref: " + k.getAttribute("paciens_ref"));
                System.out.println("Vércsoport: " + k.getElementsByTagName("vercsoport").item(0).getTextContent());
                System.out.println("Allergiák: " + k.getElementsByTagName("allergiak").item(0).getTextContent());
                System.out.println("Krónikus betegségek: " + k.getElementsByTagName("kronikusBetegsegek").item(0).getTextContent());
                System.out.println("Megjegyzés: " + k.getElementsByTagName("megjegyzes").item(0).getTextContent());
                System.out.println();
            }

            // ===== KEZELÉSEK =====
            NodeList kezelések = doc.getElementsByTagName("kezeles");
            System.out.println("=== KEZELÉSEK ===");
            for (int i = 0; i < kezelések.getLength(); i++) {
                Element kz = (Element) kezelések.item(i);
                System.out.println("Kezelés ID: " + kz.getAttribute("id"));
                System.out.println("Típus: " + kz.getElementsByTagName("tipus").item(0).getTextContent());
                System.out.println("Leírás: " + kz.getElementsByTagName("leiras").item(0).getTextContent());
                System.out.println("Ár: " + kz.getElementsByTagName("ar").item(0).getTextContent());
                System.out.println("Időtartam: " + kz.getElementsByTagName("idotartam").item(0).getTextContent());
                System.out.println();
            }

            // ===== IDŐPONTOK =====
            NodeList idopontok = doc.getElementsByTagName("idopont");
            System.out.println("=== IDŐPONTOK ===");
            for (int i = 0; i < idopontok.getLength(); i++) {
                Element id = (Element) idopontok.item(i);
                System.out.println("Időpont ID: " + id.getAttribute("id"));
                System.out.println("Orvos ref: " + id.getAttribute("orvos_ref"));
                System.out.println("Páciens ref: " + id.getAttribute("paciens_ref"));
                System.out.println("Dátum: " + id.getElementsByTagName("datum").item(0).getTextContent());
                System.out.println("Kezdés: " + id.getElementsByTagName("kezdesIdo").item(0).getTextContent());
                System.out.println("Befejezés: " + id.getElementsByTagName("befejezesIdo").item(0).getTextContent());
                System.out.println("Státusz: " + id.getElementsByTagName("statusz").item(0).getTextContent());
                System.out.println("Megjegyzés: " + id.getElementsByTagName("megjegyzes").item(0).getTextContent());
                System.out.println();
            }

            // ===== RECEPTEK =====
            NodeList receptek = doc.getElementsByTagName("recept");
            System.out.println("=== RECEPTEK ===");
            for (int i = 0; i < receptek.getLength(); i++) {
                Element r = (Element) receptek.item(i);
                System.out.println("Recept ID: " + r.getAttribute("id"));
                System.out.println("Orvos ref: " + r.getAttribute("orvos_ref"));
                System.out.println("Kezelés ref: " + r.getAttribute("kezeles_ref"));
                System.out.println("Gyógyszer: " + r.getElementsByTagName("gyogyszerNev").item(0).getTextContent());
                System.out.println("Adagolás: " + r.getElementsByTagName("adagolas").item(0).getTextContent());
                System.out.println("Kiállítás dátuma: " + r.getElementsByTagName("kiallitasDatum").item(0).getTextContent());
                System.out.println("Érvényesség: " + r.getElementsByTagName("ervenyesseg").item(0).getTextContent());
                System.out.println();
            }

            // ===== ELVÉGZETT KEZELÉSEK =====
            NodeList elvegzettek = doc.getElementsByTagName("elvegzettKezeles");
            System.out.println("=== ELVÉGZETT KEZELÉSEK ===");
            for (int i = 0; i < elvegzettek.getLength(); i++) {
                Element ek = (Element) elvegzettek.item(i);
                System.out.println("Páciens ref: " + ek.getAttribute("paciens_ref"));
                System.out.println("Kezelés ref: " + ek.getAttribute("kezeles_ref"));
                System.out.println("Dátum: " + ek.getElementsByTagName("datum").item(0).getTextContent());
                System.out.println("Sikeresség: " + ek.getElementsByTagName("sikeresseg").item(0).getTextContent());
                System.out.println("Megjegyzés: " + ek.getElementsByTagName("megjegyzes").item(0).getTextContent());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
