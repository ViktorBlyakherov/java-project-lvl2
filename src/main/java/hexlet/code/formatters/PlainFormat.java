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

    private static String stringify(Object obj) {
        if (obj != null && isComplexValue(obj)) {
            return "[complex value]";
        } else if (obj != null && isStringValue(obj)) {
            return "'" + obj + "'";
        } else if (obj == null) {
            return "null";
        } else {
            return obj.toString();
        }
    }
    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "";

        for (Map<String, Object> tmpMap : resultList) {
            String key = tmpMap.get("key").toString();
            String type = tmpMap.get("type").toString();
            String formattedValue = stringify(tmpMap.get("value"));
            String formattedValue1 = stringify(tmpMap.get("value1"));
            String formattedValue2 = stringify(tmpMap.get("value2"));

            switch (type) {
                case "changed":
                    resultString = resultString + "Property '" + key + "' was updated. "
                            + "From " + formattedValue1 + " to " + formattedValue2 + "\n";
                    break;
                case "added":
                    resultString = resultString + "Property '" + key
                            + "' was added with value: " + formattedValue + "\n";
                    break;
                case "deleted":
                    resultString = resultString + "Property '" + key + "' was removed\n";
                    break;
                default:
                    break;
            }
        }

        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : "";
    }
}
