package u7w4rzJSON;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class JSONValidationU7W4RZ 
{
	public static void main(String[] args) 
	{
		try {
            // JSON és Schema beolvasása
            String jsonContent = new String(Files.readAllBytes(Paths.get("./orarendU7W4RZ.json")));
            String schemaContent = new String(Files.readAllBytes(Paths.get("./orarendU7W4RZSchema.json")));

            // Jackson JSON mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonContent);

            // Schema factory (Draft-07)
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaContent);

            // Validáció futtatása
            Set<ValidationMessage> errors = schema.validate(jsonNode);

            if (errors.isEmpty()) {
                System.out.println("✔ A JSON ÉRVÉNYES a sémára!");
            } else {
                System.out.println("✘ Hibák találhatók a JSON-ban:\n");
                for (ValidationMessage err : errors) {
                    System.out.println("- " + err.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
            e.printStackTrace();
        }
	        
	}
}
