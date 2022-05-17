package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filepath1, String filepath2, String outputFormat) throws IOException {
        Map<String, Object> firstMap;
        Map<String, Object> secondMap;
        String formatFiles;

        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            formatFiles = "json";
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            formatFiles = "yml";
        } else {
            System.out.println("Unknown format!");
            return "";
        }

        firstMap = Parser.parseFiles(filepath1, formatFiles);
        secondMap = Parser.parseFiles(filepath2, formatFiles);

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

        return Formatter.formatResult(resultList, outputFormat);
    }
}
