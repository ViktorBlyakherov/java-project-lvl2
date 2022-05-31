package hexlet.code;

import java.io.IOException;
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
        String formatFirstFile = getFileFormat(filepath1);
        String formatSecondFile = getFileFormat(filepath2);

        Map<String, Object> firstMap = Parser.parseFiles(filepath1, formatFirstFile);
        Map<String, Object> secondMap = Parser.parseFiles(filepath2, formatSecondFile);

        return Formatter.formatResult(getDifference(firstMap, secondMap), outputFormat);
    }
}
