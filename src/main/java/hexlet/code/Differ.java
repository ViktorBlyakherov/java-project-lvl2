package hexlet.code;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.MapDifference.getDifference;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    /*
    Ты пишешь:
    Данный метод лучше реализовать как парсер расширения, просто вырезать расширение из имени файла и возвращать.

    Ну так я фактически и смотрю на расширение файла и возвращаю его, только без точки. Не понял твою мысль. Поясни,
    пожалуйста, подробнее что не так.

     */

    private static String getFileFormat(String filepath1, String filepath2) {

        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            return "json";
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            return "yml";
        } else {
            return "Unknown format";

        }

    }


    public static String generate(String filepath1, String filepath2, String outputFormat) throws IOException {
        Map<String, Object> firstMap;
        Map<String, Object> secondMap;

        String formatFiles = getFileFormat(filepath1, filepath2);

        firstMap = Parser.parseFiles(filepath1, formatFiles);
        secondMap = Parser.parseFiles(filepath2, formatFiles);

        return Formatter.formatResult(getDifference(firstMap, secondMap), outputFormat);
    }
}
