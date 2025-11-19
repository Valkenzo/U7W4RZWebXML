package u7w4rzJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWriteU7W4RZ 
{

	public static void main(String[] args) {
		try (FileReader reader = new FileReader("./orarendU7W4RZ.json")) {

            // parse
            JSONParser JSONParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) JSONParser.parse(reader);

            JSONObject root = (JSONObject) jsonObject.get("U7W4RZ_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("U7W4RZ Órarend 2025 ősz: \n");

            for (int i = 0; i < lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + ", " + time.get("tol") + " - " + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println();
            }

            // --- JSON output fájl készítése ---
            JSONObject outputRoot = new JSONObject();
            outputRoot.put("U7W4RZ_orarend", root);   // ugyanazt a szerkezetet mentjük

            try (FileWriter fw = new FileWriter("orarendU7W4RZ1.json")) {
                fw.write(outputRoot.toJSONString());
                fw.flush();
            }

            System.out.println("Kimeneti JSON létrehozva: orarendU7W4RZ_output.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
