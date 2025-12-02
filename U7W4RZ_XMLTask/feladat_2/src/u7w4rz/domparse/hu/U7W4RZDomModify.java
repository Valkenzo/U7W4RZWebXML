package u7w4rz.domparse.hu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class U7W4RZDomModify {

	 public static void main(String[] args) {
	        try {
	          
	        	File xmlFile = new File("./feladat_1/U7W4RZ_XML.xml");
	            System.out.println("Betöltés: " + xmlFile.getAbsolutePath());

	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();

	            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
	            System.out.println("=== Módosítások indítása ===\n");

	            // ===== 1️. Dr. Kiss Péter rendelőszámának módosítása =====
	            NodeList orvosok = doc.getElementsByTagName("orvos");
	            for (int i = 0; i < orvosok.getLength(); i++) {
	                Element o = (Element) orvosok.item(i);
	                if (o.getElementsByTagName("nev").item(0).getTextContent().equals("Dr. Kiss Péter")) {
	                    o.getElementsByTagName("rendeloSzoba").item(0).setTextContent("15");
	                    System.out.println("1️. Dr. Kiss Péter rendelőszáma 12 → 15");
	                }
	            }

	            // ===== 2️. Nagy Ádám városának módosítása =====
	            NodeList paciensek = doc.getElementsByTagName("paciens");
	            for (int i = 0; i < paciensek.getLength(); i++) {
	                Element p = (Element) paciensek.item(i);
	                if (p.getElementsByTagName("nev").item(0).getTextContent().equals("Nagy Ádám")) {
	                    Element cim = (Element) p.getElementsByTagName("cim").item(0);
	                    cim.getElementsByTagName("varos").item(0).setTextContent("Győr");
	                    System.out.println("2️. Nagy Ádám városa Budapest → Győr");
	                }
	            }

	            // ===== 3️. Ultrahang kezelés árának növelése =====
	            NodeList kezelések = doc.getElementsByTagName("kezeles");
	            for (int i = 0; i < kezelések.getLength(); i++) {
	                Element k = (Element) kezelések.item(i);
	                if (k.getElementsByTagName("tipus").item(0).getTextContent().equals("Ultrahang")) {
	                    int ar = Integer.parseInt(k.getElementsByTagName("ar").item(0).getTextContent());
	                    int ujAr = ar + 2000;
	                    k.getElementsByTagName("ar").item(0).setTextContent(Integer.toString(ujAr));
	                    System.out.println("3️. Ultrahang kezelés ára " + ar + " → " + ujAr + " Ft");
	                }
	            }

	            // ===== 4️. Első időpont státuszának módosítása =====
	            NodeList idopontok = doc.getElementsByTagName("idopont");
	            if (idopontok.getLength() > 0) {
	                Element elso = (Element) idopontok.item(0);
	                elso.getElementsByTagName("statusz").item(0).setTextContent("lezart");
	                System.out.println("4️. Az első időpont státusza lefoglalt → lezárt");
	            }

	            // ===== XML mentése =====
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "no");
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	            // új fájlba mentjük, hogy az eredeti megmaradjon
	            File outFile = new File("./feladat_2/U7W4RZ_XML_Modositott.xml");
	            transformer.transform(new DOMSource(doc), new StreamResult(outFile));

	            System.out.println("\n Módosítások elmentve: " + outFile.getAbsolutePath());

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
