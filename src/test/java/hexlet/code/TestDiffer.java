package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestDiffer {
    private String expectedNormal;
    private String expectedFirstEmpty;
    private String expectedSecondEmpty;

    @BeforeEach
    public void prepareData() {
        expectedNormal = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";

        expectedFirstEmpty = "{\n"
                + "  + chars1: [a, b, c]\n"
                + "  + chars2: false\n"
                + "  + checked: true\n"
                + "  + default: [value1, value2]\n"
                + "  + id: null\n"
                + "  + key2: value2\n"
                + "  + numbers1: [1, 2, 3, 4]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  + setting1: Another value\n"
                + "  + setting2: 300\n"
                + "  + setting3: none\n"
                + "}";

        expectedSecondEmpty = "{\n"
                + "  - chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  - checked: false\n"
                + "  - default: null\n"
                + "  - id: 45\n"
                + "  - key1: value1\n"
                + "  - numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  - setting1: Some value\n"
                + "  - setting2: 200\n"
                + "  - setting3: true\n"
                + "}";

    }

    @Test
    public void testEmptyFiles() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json", "stylish"), "{\n}");

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/empty2.yml", "stylish"), "{\n}");
    }

    @Test
    public void testNormalJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json", "stylish"), expectedNormal);
    }

    @Test
    public void testFirstEmptyJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/normal2.json", "stylish"), expectedFirstEmpty);
    }

    @Test
    public void testSecondEmptyJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/empty2.json", "stylish"), expectedSecondEmpty);
    }

    @Test
    public void testNormalYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/normal2.yml", "stylish"), expectedNormal);
    }

    @Test
    public void testFirstEmptyYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/normal2.yml", "stylish"), expectedFirstEmpty);
    }

    @Test
    public void testSecondEmptyYml() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/empty2.yml", "stylish"), expectedSecondEmpty);
    }

}
