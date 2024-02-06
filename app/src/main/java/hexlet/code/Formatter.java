package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.PlainText;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String selectFormatter(Map<String, Map<String, Object>> diffData, String format)
            throws JsonProcessingException {
        return switch (format) {
            case "plain" -> PlainText.genPlainText(diffData);
            case "json" -> Json.genJson(diffData);
            case  "stylish" -> Stylish.genStylish(diffData);
            default -> throw new RuntimeException("Invalid format: " + format);
        };
    }
}
