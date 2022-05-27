package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashMap;



public class MapDifference {
    public static List<Map<String, Object>> getDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Set<String> keys = new TreeSet<>();
        keys.addAll(firstMap.keySet());
        keys.addAll(secondMap.keySet());


        for (String key : keys) {
            Map<String, Object> tmpMap = new LinkedHashMap<>();
            tmpMap.put("key", key);

            if (!firstMap.containsKey(key)) {
                tmpMap.put("type", "added");
                tmpMap.put("value", secondMap.get(key));
            } else if (!secondMap.containsKey(key)) {
                tmpMap.put("type", "deleted");
                tmpMap.put("value", firstMap.get(key));
            } else if ((firstMap.get(key) != null && firstMap.get(key).equals(secondMap.get(key)))
                    || (firstMap.get(key) == null && secondMap.get(key) == null)) {
                tmpMap.put("type", "unchanged");
                tmpMap.put("value", firstMap.get(key));
            } else {
                tmpMap.put("type", "changed");
                tmpMap.put("value1", firstMap.get(key));
                tmpMap.put("value2", secondMap.get(key));
            }

            resultList.add(tmpMap);
        }

        resultList.sort((v1, v2) -> v1.get("key").toString().compareTo((v2.get("key").toString())));
        return resultList;
    }

}
