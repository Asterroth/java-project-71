package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainText {
    public static String genPlainText(Map<String, Map<String, Object>> mapDiff) {
        StringBuilder result = new StringBuilder();

        for (String key: mapDiff.keySet()) {
            Map<String, Object> map = mapDiff.get(key);

            if (map.get("status").equals("added")) {
                result.append("\nProperty ").append(stringify(key)).append(" was added with value: ").
                        append(stringify(map.get("value2")));
            } else if (map.get("status").equals("deleted")) {
                result.append("\nProperty ").append(stringify(key)).append(" was removed");
            } else if (map.get("status").equals("changed")) {
                result.append("\nProperty ").append(stringify(key)).append(" was updated. From ").
                        append(stringify(map.get("value1"))).append(" to ").
                        append(stringify(map.get("value2")));
            }
        }

        return result.delete(0, 1).toString();
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}