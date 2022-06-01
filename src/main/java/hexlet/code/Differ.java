package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.MapDifference.getDifference;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getFileFormat(String filepath) {
        if (filepath.indexOf(".") >= 0) {
            return filepath.substring(filepath.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        String formatFile = getFileFormat(filePath);
        String fileData = Files.readString(Paths.get(filePath));
        return Parser.parseFiles(fileData, formatFile);
    }

    public static String generate(String filepath1, String filepath2, String outputFormat) throws IOException {
        Map<String, Object> firstMap = getData(filepath1);
        Map<String, Object> secondMap = getData(filepath2);

        return Formatter.formatResult(getDifference(firstMap, secondMap), outputFormat);
    }
}
