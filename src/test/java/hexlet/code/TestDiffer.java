package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestDiffer {
    private static String expectedNormalStylish;
    private static String expectedFirstEmptyStylish;
    private static String expectedSecondEmptyStylish;
    private static String expectedNormalPlain;
    private static String expectedFirstEmptyPlain;
    private static String expectedSecondEmptyPlain;
    private static String expectedNormalJson;
    private static String expectedFirstEmptyJson;
    private static String expectedSecondEmptyJson;


    @BeforeAll
    public static void prepareData() throws IOException {
        expectedNormalJson = Files.readString(Paths.get("./src/test/resources/normaljson.txt"));

        expectedFirstEmptyJson = Files.readString(Paths.get("./src/test/resources/firstemptyjson.txt"));

        expectedSecondEmptyJson = Files.readString(Paths.get("./src/test/resources/secondemptyjson.txt"));

        expectedNormalStylish = Files.readString(Paths.get("./src/test/resources/normalstylish.txt"));

        expectedFirstEmptyStylish = Files.readString(Paths.get("./src/test/resources/firstemptystylish.txt"));

        expectedSecondEmptyStylish = Files.readString(Paths.get("./src/test/resources/secondemptystylish.txt"));

        expectedNormalPlain = Files.readString(Paths.get("./src/test/resources/normalplain.txt"));

        expectedFirstEmptyPlain = Files.readString(Paths.get("./src/test/resources/firstemptyplain.txt"));

        expectedSecondEmptyPlain = Files.readString(Paths.get("./src/test/resources/secondemptyplain.txt"));
    }

    @Test
    public void testEmptyFiles() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json", "stylish"), "{\n}");

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/empty2.yml", "plain"), "");

        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json", "json"), "[]");

    }


    @Test
    public void testNormalJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json", "stylish"), expectedNormalStylish);

        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json"), expectedNormalStylish);

        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json", "plain"), expectedNormalPlain);

        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json", "json"), expectedNormalJson);

    }

    @Test
    public void testFirstEmptyJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/normal2.json", "stylish"), expectedFirstEmptyStylish);

        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/normal2.json", "plain"), expectedFirstEmptyPlain);

        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/normal2.json", "json"), expectedFirstEmptyJson);
    }

    @Test
    public void testSecondEmptyJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/empty2.json", "stylish"), expectedSecondEmptyStylish);

        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/empty2.json", "plain"), expectedSecondEmptyPlain);

        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/empty2.json", "json"), expectedSecondEmptyJson);

    }

    @Test
    public void testNormalYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/normal2.yml", "stylish"), expectedNormalStylish);

        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/normal2.yml", "plain"), expectedNormalPlain);

        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/normal2.yml", "json"), expectedNormalJson);
    }

    @Test
    public void testFirstEmptyYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/normal2.yml", "stylish"), expectedFirstEmptyStylish);

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/normal2.yml", "plain"), expectedFirstEmptyPlain);

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/normal2.yml", "json"), expectedFirstEmptyJson);
    }

    @Test
    public void testSecondEmptyYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/empty2.yml", "stylish"), expectedSecondEmptyStylish);

        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/empty2.yml", "plain"), expectedSecondEmptyPlain);

        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/empty2.yml", "json"), expectedSecondEmptyJson);
    }
}
