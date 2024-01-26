package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;

public class Parser {
    public static Map<String, Object> getData(String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        File file = new File(String.valueOf(path));
        String dataFormat = FilenameUtils.getExtension(path.toString());

        return switch (dataFormat) {
            case "yml", "yaml" -> parseYaml(file);
            case "json" -> parseJson(file);
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        };
    }

    public static Map<String, Object> parseJson(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parseYaml(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}
