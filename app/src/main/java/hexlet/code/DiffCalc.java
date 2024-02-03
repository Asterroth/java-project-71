package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffCalc {
    public static Map<String, Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            Map<String, Object> element = new LinkedHashMap<>();

            if (!data1.containsKey(key)) {
                element.put("status", "added");
                element.put("value2", data2.get(key));
                result.put(key, element);
            } else if (!data2.containsKey(key)) {
                element.put("status", "deleted");
                element.put("value1", data1.get(key));
                result.put(key, element);
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                element.put("status", "unchanged");
                element.put("value1", data1.get(key));
                result.put(key, element);
            } else {
                element.put("status", "changed");
                element.put("value1", data1.get(key));
                element.put("value2", data2.get(key));
                result.put(key, element);
            }
        }
        return result;
    }
}