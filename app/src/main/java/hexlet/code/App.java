package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0-alpha",
        description = "Compares two configuration files and shows a difference."
)

public final class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;
    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = "Show this help message and exit."
    )
    private boolean usageHelp;

    @Option(names = {"-V", "--version"},
            versionHelp = true,
            description = "Print version information and exit."
    )
    private boolean versionInfo;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    private String format;

    @Parameters(index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    private String pathToFile1;

    @Parameters(index = "1",
            paramLabel = "filepath2",
            description = "path to second file")
    private String pathToFile2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            String formattedDiff = Differ.generate(pathToFile1, pathToFile2, format);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }
}
