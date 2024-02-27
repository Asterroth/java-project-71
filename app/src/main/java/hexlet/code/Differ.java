package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = getData(getPath(filePath1));
        Map<String, Object> data2 = getData(getPath(filePath2));
        Map<String, Map<String, Object>> diffData = DiffCalc.getDiff(data1, data2);

        return Formatter.selectFormatter(diffData, format);
    }

    public static Map<String, Object> getData(Path filePath) throws Exception {
        //Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' does not exist");
        }

        String content = Files.readString(filePath).trim();
        String dataFormat = FilenameUtils.getExtension(filePath.toString());

        return Parser.parse(content, dataFormat);
    }

    public static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }
}
