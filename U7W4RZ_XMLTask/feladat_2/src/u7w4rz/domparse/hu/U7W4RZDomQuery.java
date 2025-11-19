package u7w4rz.domparse.hu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class U7W4RZDomQuery {

	 public static void main(String[] args) {
	        try {
	        	File xmlFile = new File("../feladat_1/U7W4RZ_XML.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();

	            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
	            System.out.println("==================================================");

	            // --- 1. lekérdezés ---
	            // Páciensek, akiknek az elvégzett kezelése "Ultrahang"
	            System.out.println("\n1️. Páciensek, akiknek az elvégzett kezelése 'Ultrahang':\n");
	            NodeList kezelések = doc.getElementsByTagName("kezeles");
	            String ultrahangID = null;
	            for (int i = 0; i < kezelések.getLength(); i++) {
	                Element k = (Element) kezelések.item(i);
	                if (k.getElementsByTagName("tipus").item(0).getTextContent().equalsIgnoreCase("Ultrahang")) {
	                    ultrahangID = k.getAttribute("id");
	                }
	            }
	            if (ultrahangID != null) {
	                NodeList elvegzettek = doc.getElementsByTagName("elvegzettKezeles");
	                for (int i = 0; i < elvegzettek.getLength(); i++) {
	                    Element e = (Element) elvegzettek.item(i);
	                    if (e.getAttribute("kezeles_ref").equals(ultrahangID)) {
	                        String paciensRef = e.getAttribute("paciens_ref");
	                        printPaciens(doc, paciensRef);
	                    }
	                }
	            }

	            // --- 2. lekérdezés ---
	            // Összes lezárt időpont (statusz = "lezart")
	            System.out.println("\n2️. Lezárt időpontok:\n");
	            NodeList idopontok = doc.getElementsByTagName("idopont");
	            for (int i = 0; i < idopontok.getLength(); i++) {
	                Element id = (Element) idopontok.item(i);
	                String statusz = id.getElementsByTagName("statusz").item(0).getTextContent();
	                if (statusz.equalsIgnoreCase("lezart")) {
	                    System.out.println("Időpont ID: " + id.getAttribute("id"));
	                    System.out.println("Dátum: " + id.getElementsByTagName("datum").item(0).getTextContent());
	                    System.out.println("Orvos ref: " + id.getAttribute("orvos_ref"));
	                    System.out.println("Páciens ref: " + id.getAttribute("paciens_ref"));
	                    System.out.println("Megjegyzés: " + id.getElementsByTagName("megjegyzes").item(0).getTextContent());
	                    System.out.println("---------------------------");
	                }
	            }

	            // --- 3. lekérdezés ---
	            // Kezelések, amelyek ára 13000 Ft fölött van
	            System.out.println("\n3️. Kezelések, amelyek ára 13000 Ft fölött van:\n");
	            for (int i = 0; i < kezelések.getLength(); i++) {
	                Element k = (Element) kezelések.item(i);
	                int ar = Integer.parseInt(k.getElementsByTagName("ar").item(0).getTextContent());
	                if (ar > 13000) {
	                    System.out.println("Kezelés ID: " + k.getAttribute("id"));
	                    System.out.println("Típus: " + k.getElementsByTagName("tipus").item(0).getTextContent());
	                    System.out.println("Leírás: " + k.getElementsByTagName("leiras").item(0).getTextContent());
	                    System.out.println("Ár: " + ar + " Ft");
	                    System.out.println("---------------------------");
	                }
	            }

	            // --- 4. lekérdezés ---
	            // Azon páciensek, akik Budapestiek
	            System.out.println("\n4️. Páciensek, akik Budapesten laknak:\n");
	            NodeList paciensek = doc.getElementsByTagName("paciens");
	            for (int i = 0; i < paciensek.getLength(); i++) {
	                Element p = (Element) paciensek.item(i);
	                Element cim = (Element) p.getElementsByTagName("cim").item(0);
	                String varos = cim.getElementsByTagName("varos").item(0).getTextContent();
	                if (varos.equalsIgnoreCase("Budapest")) {
	                    System.out.println("Név: " + p.getElementsByTagName("nev").item(0).getTextContent());
	                    System.out.println("TAJ: " + p.getElementsByTagName("taj").item(0).getTextContent());
	                    System.out.println("Cím: " + varos + ", " +
	                        cim.getElementsByTagName("utca").item(0).getTextContent() + " " +
	                        cim.getElementsByTagName("hazszam").item(0).getTextContent());
	                    System.out.println("---------------------------");
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Kis segédfüggvény egy páciens adataihoz
	    private static void printPaciens(Document doc, String paciensID) {
	        NodeList paciensek = doc.getElementsByTagName("paciens");
	        for (int i = 0; i < paciensek.getLength(); i++) {
	            Element p = (Element) paciensek.item(i);
	            if (p.getAttribute("id").equals(paciensID)) {
	                System.out.println("Páciens ID: " + p.getAttribute("id"));
	                System.out.println("Név: " + p.getElementsByTagName("nev").item(0).getTextContent());
	                System.out.println("TAJ: " + p.getElementsByTagName("taj").item(0).getTextContent());
	                System.out.println("---------------------------");
	            }
	        }
	    }
}
