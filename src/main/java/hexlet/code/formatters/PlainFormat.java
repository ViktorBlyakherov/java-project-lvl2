package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class PlainFormat {
    public static String formatOutput(List<Differ.KeyDifference> resultList) {
        String resultString = "";
        for (int i = 0; i < resultList.size(); i++) {
            Differ.KeyDifference tmpObj = resultList.get(i);
            String firstValue;
            String secondValue;

            if (tmpObj.getFirstValue() == null) {
                firstValue = "null";
            } else if (tmpObj.getFirstValue() instanceof Map<?, ?> || tmpObj.getFirstValue() instanceof List) {
                firstValue = "[complex value]";
            } else if (tmpObj.getFirstValue() instanceof String) {
                firstValue = "'" + tmpObj.getFirstValue().toString() + "'";
            } else {
                firstValue = tmpObj.getFirstValue().toString();
            }

            if (tmpObj.getSecondValue() == null) {
                secondValue = "null";
            } else if (tmpObj.getSecondValue() instanceof Map<?, ?> || tmpObj.getSecondValue() instanceof List) {
                secondValue = "[complex value]";
            } else if (tmpObj.getSecondValue() instanceof String) {
                secondValue = "'" + tmpObj.getSecondValue().toString() + "'";
            } else {
                secondValue = tmpObj.getSecondValue().toString();
            }

            if (tmpObj.isInFirstFile() && tmpObj.isInSecondFile()) {
                if (!firstValue.equals(secondValue)) {
                    resultString = resultString + "Property '" + tmpObj.getKeyValue() + "' was updated. "
                                    + "From " + firstValue + " to " + secondValue + "\n";
                } else {
                    if (firstValue == "[complex value]" && secondValue == "[complex value]"
                            && !tmpObj.getFirstValue().equals(tmpObj.getSecondValue())) {
                        resultString = resultString + "Property '" + tmpObj.getKeyValue() + "' was updated. "
                                + "From " + firstValue + " to " + secondValue + "\n";
                    }
                }
            } else if (tmpObj.isInFirstFile() && !tmpObj.isInSecondFile()) {
                resultString = resultString + "Property '" + tmpObj.getKeyValue() + "' was removed\n";
            } else if (tmpObj.isInSecondFile() && !tmpObj.isInFirstFile()) {
                resultString = resultString + "Property '" + tmpObj.
                        getKeyValue() + "' was added with value: " + secondValue + "\n";
            }
        }
        return resultString;
    }
}
