import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @Test
    void differTestJson() throws Exception {
        String resultJson = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        String path1 = getFixturePath("file1.json").toString();
        String path2 = getFixturePath("file2.json").toString();
        assertEquals(resultJson, Differ.generate(path1, path2));
    }

    @Test
    void differTestYaml() throws Exception {
        String resultJson = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        String path1 = getFixturePath("file1.yml").toString();
        String path2 = getFixturePath("file2.yml").toString();
        assertEquals(resultJson, Differ.generate(path1, path2));
    }
}
