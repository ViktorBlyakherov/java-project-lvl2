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


    public static String generate(String filepath1, String filepath2, String outputFormat) throws IOException {
        String formatFile = getFileFormat(filepath1);
        String fileData = Files.readString(Paths.get(filepath1));
        Map<String, Object> firstMap = Parser.parseFiles(fileData, formatFile);

        formatFile = getFileFormat(filepath2);
        fileData = Files.readString(Paths.get(filepath2));
        Map<String, Object> secondMap = Parser.parseFiles(fileData, formatFile);

        return Formatter.formatResult(getDifference(firstMap, secondMap), outputFormat);
    }
}
