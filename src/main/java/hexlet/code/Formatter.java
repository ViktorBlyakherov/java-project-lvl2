package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatResult(List<Map<String, Object>> resultList, String formatOutput)
            throws JsonProcessingException {
        switch (formatOutput) {
            case "stylish":
                return StylishFormat.formatOutput(resultList);
            case "plain":
                return PlainFormat.formatOutput(resultList);
            case "json":
                return JsonFormat.formatOutput(resultList);
            default:
                return "Unknown format!";
        }
    }
}
