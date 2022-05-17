package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;

public class StylishFormat {
    public static String formatOutput(List<Differ.KeyDifference> resultList) {
        String resultString = "{\n";
        for (int i = 0; i < resultList.size(); i++) {
            Differ.KeyDifference tmpObj = resultList.get(i);
            String firstValue = "";
            String secondValue = "";

            if (tmpObj.getFirstValue() == null) {
                firstValue = "null";
            } else {
                firstValue = tmpObj.getFirstValue().toString();
            }

            if (tmpObj.getSecondValue() == null) {
                secondValue = "null";
            } else {
                secondValue = tmpObj.getSecondValue().toString();
            }

            if (tmpObj.isInFirstFile() && tmpObj.isInSecondFile()) {
                if (firstValue.equals(secondValue)) {
                    resultString = resultString + "    " + tmpObj.getKeyValue() + ": " + firstValue + "\n";
                } else {
                    resultString = resultString + "  - " + tmpObj.getKeyValue() + ": " + firstValue + "\n";
                    resultString = resultString + "  + " + tmpObj.getKeyValue() + ": " + secondValue + "\n";
                }
            } else if (tmpObj.isInFirstFile()) {
                resultString = resultString + "  - " + tmpObj.getKeyValue() + ": " + firstValue + "\n";
            } else {
                resultString = resultString + "  + " + tmpObj.getKeyValue() + ": " + secondValue + "\n";
            }
        }
        resultString = resultString + "}";
        return resultString;
    }
}
