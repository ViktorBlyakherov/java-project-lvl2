package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormat {
    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "";
        for (int i = 0; i < resultList.size(); i++) {

            Map<String, Object> tmpMap = resultList.get(i);
            String firstValue;
            String secondValue;

            if (tmpMap.get("type").equals("changed")) {
                String value1;
                String value2;
                if (tmpMap.get("value1") != null && (tmpMap.get("value1") instanceof Map<?, ?>
                        || tmpMap.get("value1") instanceof List)) {
                    value1 = "[complex value]";
                } else if (tmpMap.get("value1") != null && tmpMap.get("value1") instanceof String) {
                    value1 = "'" + tmpMap.get("value1") + "'";
                } else {
                    value1 = tmpMap.get("value1") == null ? "null" : tmpMap.get("value1").toString();

                }

                if (tmpMap.get("value2") != null && (tmpMap.get("value2") instanceof Map<?, ?>
                        || tmpMap.get("value2") instanceof List)) {
                    value2 = "[complex value]";
                } else if (tmpMap.get("value2") != null && tmpMap.get("value2") instanceof String) {
                    value2 = "'" + tmpMap.get("value2") + "'";
                } else {
                    value2 = tmpMap.get("value2") == null ? "null" : tmpMap.get("value2").toString();
                }

                resultString = resultString + "Property '" + tmpMap.get("key") + "' was updated. "
                        + "From " + value1 + " to " + value2 + "\n";

            } else if (tmpMap.get("type").equals("added")) {
                String value;
                if (tmpMap.get("value") != null && (tmpMap.get("value") instanceof Map<?, ?>
                        || tmpMap.get("value") instanceof List)) {
                    value = "[complex value]";
                } else if (tmpMap.get("value") != null && tmpMap.get("value") instanceof String) {
                    value = "'" + tmpMap.get("value") + "'";
                } else {
                    value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                }
                resultString = resultString + "Property '" + tmpMap.get("key")
                        + "' was added with value: " + value + "\n";

            } else if (tmpMap.get("type").equals("deleted")) {
                resultString = resultString + "Property '" + tmpMap.get("key") + "' was removed\n";
            }
        }

        return resultString.substring(0, resultString.length() - 1);
    }
}
