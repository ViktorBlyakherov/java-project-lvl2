package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormat {

    private static String stringify(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            return obj.toString();
        }
    }
    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "{\n";
        for (Map<String, Object> tmpMap : resultList) {

            String key = tmpMap.get("key").toString();
            String type = tmpMap.get("type").toString();
            String formattedValue = stringify(tmpMap.get("value"));
            String formattedValue1 = stringify(tmpMap.get("value1"));
            String formattedValue2 = stringify(tmpMap.get("value2"));

            switch (type) {
                case "unchanged":
                    resultString = resultString + "    " + key + ": " + formattedValue + "\n";
                    break;
                case "added":
                    resultString = resultString + "  + " + key + ": " + formattedValue + "\n";
                    break;
                case "changed":
                    resultString = resultString + "  - " + key + ": " + formattedValue1 + "\n";
                    resultString = resultString + "  + " + key + ": " + formattedValue2 + "\n";
                    break;
                case "deleted":
                    resultString = resultString + "  - " + key + ": " + formattedValue + "\n";
                    break;
                default:
                    break;
            }
        }
        resultString = resultString + "}";
        return resultString;
    }
}
