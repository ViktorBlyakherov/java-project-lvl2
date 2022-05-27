package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static Map<String, Object> parseJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fileData = Files.readString(Paths.get(filePath));
        if (!fileData.isBlank()) {
            return mapper.readValue(fileData, new TypeReference<Map<String, Object>>() { });
        } else {
            return new HashMap<>();
        }
    }

    private static Map<String, Object> parseYaml(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String fileData = Files.readString(Paths.get(filePath));

        if (!fileData.isBlank()) {
            return mapper.readValue(fileData, new TypeReference<Map<String, Object>>() { });
        } else {
            return new HashMap<>();
        }

    }

    public static Map<String, Object> parseFiles(String filePath, String formatFile) throws IOException {

        ObjectMapper mapper;

        if (formatFile.equals("json")) {
            return parseJson(filePath);
        } else if (formatFile.equals("yml")) {
            return parseYaml(filePath);
        } else {
            throw new Error("Invalid format");
        }
    }
}
