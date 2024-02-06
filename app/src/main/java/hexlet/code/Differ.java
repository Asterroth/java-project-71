package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.getData(filePath1);
        Map<String, Object> data2 = Parser.getData(filePath2);
        Map<String, Map<String, Object>> diffData = DiffCalc.getDiff(data1, data2);

        return Formatter.selectFormatter(diffData, format);
    }
}
