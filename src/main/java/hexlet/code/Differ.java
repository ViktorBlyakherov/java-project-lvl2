package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Differ {
    public static class KeyDifference {
        private String keyValue = "";
        private boolean isInFirstFile = false;
        private boolean isInSecondFile = false;
        private Object firstValue = "";
        private Object secondValue = "";

        public final String getKeyValue() {
            return keyValue;
        }

        public final boolean isInFirstFile() {
            return isInFirstFile;
        }

        public final boolean isInSecondFile() {
            return isInSecondFile;
        }

        public final Object getFirstValue() {
            return firstValue;
        }

        public final Object getSecondValue() {
            return secondValue;
        }
    }



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

        List<KeyDifference> resultList = new ArrayList<>();

        for (String key : firstMap.keySet()) {
            KeyDifference tempObj = new KeyDifference();
            tempObj.keyValue = key;
            tempObj.isInFirstFile = true;
            tempObj.firstValue = firstMap.get(key);
            if (secondMap.containsKey(key)) {
                tempObj.isInSecondFile = true;
                tempObj.secondValue = secondMap.get(key);
            }
            resultList.add(tempObj);
        }

        for (String key : secondMap.keySet()) {
            if (!firstMap.containsKey(key)) {
                KeyDifference tempObj = new KeyDifference();
                tempObj.keyValue = key;
                tempObj.isInSecondFile = true;
                tempObj.secondValue = secondMap.get(key);
                resultList.add(tempObj);
            }
        }

        resultList.sort((v1, v2) -> v1.keyValue.compareTo(v2.keyValue));

        return Formatter.formatResult(resultList, outputFormat);
    }
}
