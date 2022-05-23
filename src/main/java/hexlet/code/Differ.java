package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getFileFormat(String filepath1, String filepath2) {

        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            return "json";
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            return "yml";
        } else {
            return "Unknown format";

        }

    }

    public static List<Map<String, Object>> getDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (String key : firstMap.keySet()) {
            Map<String, Object> tmpMap = new LinkedHashMap<>();
            tmpMap.put("key", key);

            if (secondMap.containsKey(key)) {
                if (firstMap.get(key) == null) {
                    if (secondMap.get(key) == null) {
                        tmpMap.put("type", "unchanged");
                        tmpMap.put("value", firstMap.get(key));
                    } else {
                        tmpMap.put("type", "changed");
                        tmpMap.put("value1", firstMap.get(key));
                        tmpMap.put("value2", secondMap.get(key));
                    }
                } else if (firstMap.get(key).equals(secondMap.get(key))) {
                    tmpMap.put("type", "unchanged");
                    tmpMap.put("value", firstMap.get(key));
                } else {
                    tmpMap.put("type", "changed");
                    tmpMap.put("value1", firstMap.get(key));
                    tmpMap.put("value2", secondMap.get(key));
                }
            } else {
                tmpMap.put("type", "deleted");
                tmpMap.put("value", firstMap.get(key));
            }

            resultList.add(tmpMap);
        }

        for (String key : secondMap.keySet()) {
            if (!firstMap.containsKey(key)) {
                Map<String, Object> tmpMap = new LinkedHashMap<>();
                tmpMap.put("key", key);
                tmpMap.put("type", "added");
                tmpMap.put("value", secondMap.get(key));
                resultList.add(tmpMap);
            }
        }

        resultList.sort((v1, v2) -> v1.get("key").toString().compareTo((v2.get("key").toString())));
        return resultList;
    }

    public static String generate(String filepath1, String filepath2, String outputFormat) throws IOException {
        Map<String, Object> firstMap;
        Map<String, Object> secondMap;
        String formatFiles;

        formatFiles = getFileFormat(filepath1, filepath2);

        if (formatFiles.equals("Unknown format")) {
            System.out.println(formatFiles);
            return "";
        }

        firstMap = Parser.parseFiles(filepath1, formatFiles);
        secondMap = Parser.parseFiles(filepath2, formatFiles);

        return Formatter.formatResult(getDifference(firstMap, secondMap), outputFormat);
    }
}
