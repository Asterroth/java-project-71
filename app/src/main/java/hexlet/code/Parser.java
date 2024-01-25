package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map<String, Object> parseData(File file, String fileFormat) throws Exception {
        return switch (fileFormat) {
            case "yml", "yaml" -> parseYaml(file);
            case "json" -> parseJson(file);
            default -> throw new Exception("Unknown format: '" + fileFormat + "'");
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
