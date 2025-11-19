package u7w4rzJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadU7W4RZ {

	 public static void main(String[] args) {
	        
		 try (FileReader reader = new FileReader("./orarendU7W4RZ.json"))
	              {

	            // parse
	            JSONParser JSONParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) JSONParser.parse(reader);

	            JSONObject root = (JSONObject) jsonObject.get("U7W4RZ_orarend");
	            JSONArray lessons = (JSONArray) root.get("ora");

	            System.out.println("U7W4RZ Órarend 2025 ősz: \n");
	            

	            for (int i = 0; i < lessons.size(); i++) {
	                JSONObject lesson = (JSONObject) lessons.get(i);
	                JSONObject time = (JSONObject) lesson.get("idopont");

	                String blokk =
	                        "------------------------------\n" +
	                        "Tárgy: " + lesson.get("targy") + "\n" +
	                        "Időpont: " + time.get("nap") + ", " + time.get("tol") + " - " + time.get("ig") + "\n" +
	                        "Helyszín: " + lesson.get("helyszin") + "\n" +
	                        "Oktató: " + lesson.get("oktato") + "\n" +
	                        "Szak: " + lesson.get("szak") + "\n" +
	                        "------------------------------\n";

	                // konzolra
	                System.out.println(blokk);

	            }


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
}
