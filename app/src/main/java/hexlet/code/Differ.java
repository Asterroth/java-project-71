package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2) throws Exception {
        List<String> resultList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

//        Path path1 = Paths.get(pathToFile1).toAbsolutePath().normalize();
//        Path path2 = Paths.get(pathToFile2).toAbsolutePath().normalize();
//
//        File file1 = new File(String.valueOf(path1));
//        File file2 = new File(String.valueOf(path2));
//
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> jsonData1 = mapper.readValue(file1, new TypeReference<>() { });
//        Map<String, Object> jsonData2 = mapper.readValue(file2, new TypeReference<>() { });

        Map<String, Object> data1 = getData(pathToFile1);
        Map<String, Object> data2 = getData(pathToFile2);

        SortedSet<String> totalKeys = new TreeSet<>(data1.keySet());
        totalKeys.addAll(data2.keySet());

        for (String key : totalKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (value1.equals(value2)) {
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

    public static Map<String, Object> getData(String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        File file = new File(String.valueOf(path));
        String dataFormat = FilenameUtils.getExtension(path.toString());
        return Parser.parseData(file, dataFormat);
    }
}
