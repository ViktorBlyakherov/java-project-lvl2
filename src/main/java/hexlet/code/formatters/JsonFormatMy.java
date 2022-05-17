package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatMy {
    public static String formatOutput(List<Map<String, Object>> resultList) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> tmpMap = resultList.get(i);
            resultString = resultString + mapper.writeValueAsString(tmpMap) + "\n";

        }
        return resultString;
    }
}
