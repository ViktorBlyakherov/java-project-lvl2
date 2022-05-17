package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormat {
    public static String formatOutput(List<Map<String, Object>> resultList) {
        String resultString = "{\n";
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> tmpMap = resultList.get(i);
            String value;

            if (tmpMap.get("type").equals("unchanged")) {
                value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                resultString = resultString + "    " + tmpMap.get("key") + ": " + value + "\n";
            } else if (tmpMap.get("type").equals("changed")) {
                value = tmpMap.get("value1") == null ? "null" : tmpMap.get("value1").toString();
                resultString = resultString + "  - " + tmpMap.get("key") + ": " + value + "\n";
                value = tmpMap.get("value2") == null ? "null" : tmpMap.get("value2").toString();
                resultString = resultString + "  + " + tmpMap.get("key") + ": " + value + "\n";
            } else if (tmpMap.get("type").equals("added")) {
                value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                resultString = resultString + "  + " + tmpMap.get("key") + ": " + value + "\n";
            } else if (tmpMap.get("type").equals("deleted")) {
                value = tmpMap.get("value") == null ? "null" : tmpMap.get("value").toString();
                resultString = resultString + "  - " + tmpMap.get("key") + ": " + value + "\n";
            }
        }
        resultString = resultString + "}";
        return resultString;
    }
}
