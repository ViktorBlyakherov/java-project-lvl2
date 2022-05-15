package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    private class KeyDifference {
        private String keyValue = "";
        private boolean isInFirstFile = false;
        private boolean isInSecondFile = false;
        private String firstValue = "";
        private String secondValue = "";

        @Override
        public String toString() {
            String resultString = "";
            if (isInFirstFile && isInSecondFile) {
                if (!firstValue.equals(secondValue)) {
                    resultString = resultString + "- " + keyValue + ": " + firstValue + "\n";
                    resultString = resultString + "+ " + keyValue + ": " + secondValue + "\n";
                } else {
                    resultString = resultString + "  " + keyValue + ": " + firstValue + "\n";
                }
            } else if (isInFirstFile && !isInSecondFile) {
                resultString = resultString + "- " + keyValue + ": " + firstValue + "\n";
            } else if (isInSecondFile && !isInFirstFile) {
                resultString = resultString + "+ " + keyValue + ": " + secondValue + "\n";
            }
            return resultString;
        }
    }



    public final String generate(String filepath1, String filepath2) throws IOException {
        Map<String, String> firstMap = new HashMap<>();
        Map<String, String> secondMap = new HashMap<>();
        String formatFiles = "";

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
        String resultString = "{\n";
        for (KeyDifference diff : resultList) {
            resultString = resultString + diff.toString();
        }
        resultString = resultString + "}";

        return resultString;
    }
}
