package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormat {
    private static boolean isComplexValue(Object valueObj) {
        return valueObj instanceof Map<?, ?> || valueObj instanceof List;
    }

    private static boolean isStringValue(Object valueObj) {
        return valueObj instanceof String;
    }


    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "";
        for (Map<String, Object> tmpMap : resultList) {

            switch (tmpMap.get("type").toString()) {
                case "changed":
                    String value1;
                    String value2;
                    if (tmpMap.get("value1") != null && isComplexValue(tmpMap.get("value1"))) {
                        value1 = "[complex value]";
                    } else if (tmpMap.get("value1") != null && isStringValue(tmpMap.get("value1"))) {
                        value1 = "'" + tmpMap.get("value1") + "'";
                    } else {
                        value1 = tmpMap.get("value1") == null ? "null" : tmpMap.get("value1").toString();

                    }

                    if (tmpMap.get("value2") != null && isComplexValue(tmpMap.get("value2"))) {
                        value2 = "[complex value]";
                    } else if (tmpMap.get("value2") != null && isStringValue(tmpMap.get("value2"))) {
                        value2 = "'" + tmpMap.get("value2") + "'";
                    } else {
                        value2 = tmpMap.get("value2") == null ? "null" : tmpMap.get("value2").toString();
                    }

                    resultString = resultString + "Property '" + tmpMap.get("key") + "' was updated. "
                            + "From " + value1 + " to " + value2 + "\n";
                    break;
                case "added":
                    String value;
                    if (tmpMap.get("value") != null && isComplexValue(tmpMap.get("value"))) {
                        value = "[complex value]";
                    } else if (tmpMap.get("value") != null && isStringValue(tmpMap.get("value"))) {
                        value = "'" + tmpMap.get("value") + "'";
                    } else {
                        value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                    }
                    resultString = resultString + "Property '" + tmpMap.get("key")
                            + "' was added with value: " + value + "\n";
                    break;
                case "deleted":
                    resultString = resultString + "Property '" + tmpMap.get("key") + "' was removed\n";
                    break;
                default:
                    break;
            }
        }

        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : "";
    }
}
