package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String genStylish(Map<String, Map<String, Object>> diffData) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key: diffData.keySet()) {
            Map<String, Object> map = diffData.get(key);
            String status = map.get("type").toString();
            String prefix = buildPrefix(status);

            switch (status) {
                case "added" -> result.append(prefix)
                                      .append(key)
                                      .append(": ")
                                      .append(map.get("value2"))
                                      .append("\n");
                case "deleted", "unchanged" -> result.append(prefix)
                                        .append(key)
                                        .append(": ")
                                        .append(map.get("value1"))
                                        .append("\n");
                default -> {
                    result.append(buildPrefix("deleted"))
                            .append(key)
                            .append(": ")
                            .append(map.get("value1"))
                            .append("\n");
                    result.append(buildPrefix("added"))
                            .append(key)
                            .append(": ")
                            .append(map.get("value2"))
                            .append("\n");
                }
            }
//            if (status.equals("added")) {
//                result.append("  + ").append(key).append(": ").append(map.get("value2")).append("\n");
//            } else if (status.equals("deleted")) {
//                result.append("  - ").append(key).append(": ").append(map.get("value1")).append("\n");
//            } else if (status.equals("unchanged")) {
//                result.append("    ").append(key).append(": ").append(map.get("value1")).append("\n");
//            } else {
//                result.append("  - ").append(key).append(": ").append(map.get("value1")).append("\n");
//                result.append("  + ").append(key).append(": ").append(map.get("value2")).append("\n");
//            }
        }

        result.append("}");
        return result.toString();
    }

    private static String buildPrefix (String status) {
        StringBuilder prefix = new StringBuilder();
        switch (status) {
            case "added" -> prefix.append("  + ");
            case "deleted" -> prefix.append("  - ");
            default -> prefix.append("    ");
        }
        return prefix.toString();
    }
}
