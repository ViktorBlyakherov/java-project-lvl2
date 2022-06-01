package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFiles(String fileData, String formatFile) throws IOException {

        ObjectMapper mapper;

        if (formatFile.equals("json")) {
            mapper = new ObjectMapper();
        } else if (formatFile.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new Error("Invalid format");
        }
        if (!fileData.isBlank()) {
            return mapper.readValue(fileData, new TypeReference<Map<String, Object>>() { });
        } else {
            return new HashMap<>();
        }
    }
}
