package hexlet.code;

import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.List;

public class Formatter {
    public static String formatResult(List<Differ.KeyDifference> resultList, String formatOutput) {
        if (formatOutput.equals("stylish")) {
            return StylishFormat.formatOutput(resultList);
        }
        if (formatOutput.equals("plain")) {
            return PlainFormat.formatOutput(resultList);
        }

        return "Unknown format!";
    }
}
