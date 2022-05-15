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
    /*
    Return true on successful parsing and
    Return false if file extension is not valid
     */
    public static Map<String, String> parseFiles(String filePath, String formatFile) throws IOException {

        ObjectMapper mapper;

        if (formatFile.equals("json")) {
            mapper = new ObjectMapper();
        } else if (formatFile.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            return new HashMap<String, String>();
        }
        String fileData = Files.readString(Paths.get(filePath));

        if (!fileData.isBlank()) {
            return mapper.readValue(fileData, new TypeReference<Map<String, String>>() { });
        } else {
            return new HashMap<String, String>();
        }
    }
}
