
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;



@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        headerHeading = "Demonstration Usage:%n%n")

public class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit")
    private boolean isHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean isVersionRequested;
/*
  Эти две переменные удалить нельзя. Picocli требует, чтобы каждая опция присваивалась какой-то переменной.
*/
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0", arity = "1",
            description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", arity = "1",
            description = "path to second file")
    private String filepath2;


    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public final Integer call() throws Exception {

        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }
}
