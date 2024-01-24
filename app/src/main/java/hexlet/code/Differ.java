package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        List<String> resultList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        Path path1 = Paths.get(pathToFile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathToFile2).toAbsolutePath().normalize();

        File file1 = new File(String.valueOf(path1));
        File file2 = new File(String.valueOf(path2));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData1 = mapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> jsonData2 = mapper.readValue(file2, new TypeReference<>() { });

        SortedSet<String> totalKeys = new TreeSet<>(jsonData1.keySet());
        totalKeys.addAll(jsonData2.keySet());

        for (String key : totalKeys) {
            Object value1 = jsonData1.get(key);
            Object value2 = jsonData2.get(key);
            if (jsonData1.containsKey(key) && jsonData2.containsKey(key)) {
                if (value1.equals(value2)) {
                    resultList.add("   " + key + ": " + value1);
                } else {
                    resultList.add(" - " + key + ": " + value1);
                    resultList.add(" + " + key + ": " + value2);
                }
            } else if (jsonData2.containsKey(key)) {
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
