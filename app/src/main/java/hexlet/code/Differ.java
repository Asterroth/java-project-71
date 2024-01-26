package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2) throws Exception {
        List<String> resultList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        Map<String, Object> data1 = Parser.getData(pathToFile1);
        Map<String, Object> data2 = Parser.getData(pathToFile2);

        SortedSet<String> totalKeys = new TreeSet<>(data1.keySet());
        totalKeys.addAll(data2.keySet());

        for (String key : totalKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (Objects.equals(value1, value2)) {
                    resultList.add("   " + key + ": " + value1);
                } else {
                    resultList.add(" - " + key + ": " + value1);
                    resultList.add(" + " + key + ": " + value2);
                }
            } else if (data2.containsKey(key)) {
                resultList.add(" + " + key + ": " + value2);
            } else {
                resultList.add(" - " + key + ": " + value1);
            }
        }

        result.append("{" + "\n");
        for (String value :resultList) {
            result.append(value);
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }
}
