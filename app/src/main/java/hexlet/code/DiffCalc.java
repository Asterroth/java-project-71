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
                element.put("type", "added");
                element.put("value2", data2.get(key));
                result.put(key, element);
            } else if (!data2.containsKey(key)) {
                element.put("type", "deleted");
                element.put("value1", data1.get(key));
                result.put(key, element);
            } else if (isEqual(data1.get(key), data2.get(key))) {
                element.put("type", "unchanged");
                element.put("value1", data1.get(key));
                result.put(key, element);
            } else {
                element.put("type", "changed");
                element.put("value1", data1.get(key));
                element.put("value2", data2.get(key));
                result.put(key, element);
            }
        }
        return result;
    }

    public static boolean isEqual(Object data1, Object data2) {
        if (data1 == null || data2 == null) {
            return data1 == data2;
        }

        return data1.equals(data2);
    }
}
