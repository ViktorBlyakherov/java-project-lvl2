package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormat {
    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "{\n";
        for (Map<String, Object> tmpMap : resultList) {
            String value;
/*
Перед switch получать значение нельзя, так как разные ключи для разных типов данных.
 */

            switch (tmpMap.get("type").toString()) {
                case "unchanged":
                    value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                    resultString = resultString + "    " + tmpMap.get("key") + ": " + value + "\n";
                    break;
                case "added":
                    value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                    resultString = resultString + "  + " + tmpMap.get("key") + ": " + value + "\n";
                    break;
                case "changed":
                    value = tmpMap.get("value1") == null ? "null" : tmpMap.get("value1").toString();
                    resultString = resultString + "  - " + tmpMap.get("key") + ": " + value + "\n";
                    value = tmpMap.get("value2") == null ? "null" : tmpMap.get("value2").toString();
                    resultString = resultString + "  + " + tmpMap.get("key") + ": " + value + "\n";
                    break;
                case "deleted":
                    value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                    resultString = resultString + "  - " + tmpMap.get("key") + ": " + value + "\n";
                    break;
                default:
                    break;
            }
        }
        resultString = resultString + "}";
        return resultString;
    }
}
