package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatMy;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatResult(List<Map<String, Object>> resultList, String formatOutput)
            throws JsonProcessingException {
        if (formatOutput.equals("stylish")) {
            return StylishFormat.formatOutput(resultList);
        }
        if (formatOutput.equals("plain")) {
            return PlainFormat.formatOutput(resultList);
        }

        if (formatOutput.equals("json")) {
            return JsonFormatMy.formatOutput(resultList);
        }

        return "Unknown format!";
    }
}
