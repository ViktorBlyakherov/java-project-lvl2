
package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;



@Command(
        name="gendiff",
        description="Compares two configuration files and shows a difference.",
        headerHeading="Demonstration Usage:%n%n")

public class App implements Callable<Integer>{
    @Option(names = {"-h", "--help"}, usageHelp=true, description = "Show this help message and exit.")
    boolean usageHelpRequested;
    @Option(names = {"-V", "--version"}, versionHelp=true, description = "Print version information and exit.")
    boolean versionHelpRequested;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format;

    @Parameters(index = "0", arity = "1",
            description = "path to first file")
    String filepath1;

    @Parameters(index = "1", arity = "1",
            description = "path to second file")
    String filepath2;

    public static String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String fileFirstJson = Files.readString(Paths.get(filepath1));
        String fileSecondJson = Files.readString(Paths.get(filepath2));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> mapFirst
                = mapper.readValue(fileFirstJson, new TypeReference<Map<String,String>>(){});

        Map<String, String> mapSecond
                = mapper.readValue(fileSecondJson, new TypeReference<Map<String,String>>(){});
        System.out.println(new Differ().generate(mapFirst, mapSecond));
//        System.out.println(map.toString());
        return 0;
    }
}
