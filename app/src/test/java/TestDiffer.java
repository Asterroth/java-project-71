
import hexlet.code.Differ;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class TestDiffer {
    private static String expectedJson;
    private static String expectedPlain;
    private static String expectedStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedJson = readFixture("expected-json");
        expectedPlain = readFixture("expected-plain");
        expectedStylish = readFixture("expected-stylish");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String path1 = getFixturePath("file1." + format).toString();
        String path2 = getFixturePath("file2." + format).toString();

        assertEquals(expectedStylish, Differ.generate(path1, path2));
        assertEquals(expectedStylish, Differ.generate(path1, path2, "stylish"));
        assertEquals(expectedPlain, Differ.generate(path1, path2, "plain"));
        assertEquals(expectedJson, Differ.generate(path1, path2, "json"));
    }
}