package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0-alpha",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Runnable {

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
    public void run() {
        System.out.println("Hello World!");
    }
}
