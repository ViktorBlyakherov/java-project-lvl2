package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormat {
    public static String formatOutput(List<Map<String, Object>> resultList) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        for (Map<String, Object> tmpMap : resultList) {
            resultString = resultString + mapper.writeValueAsString(tmpMap) + "\n";

        }
/*
        return mapper.writeValueAsString(resultList);
Так написать можно, но вывод без перевода строк, абсолютно нечитаем.
 */
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : "";
    }
}
