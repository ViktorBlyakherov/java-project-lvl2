package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestDiffer {
    private String expectedNormalStylish;
    private String expectedFirstEmptyStylish;
    private String expectedSecondEmptyStylish;
    private String expectedNormalPlain;
    private String expectedFirstEmptyPlain;
    private String expectedSecondEmptyPlain;
    private String expectedNormalJson;
    private String expectedFirstEmptyJson;
    private String expectedSecondEmptyJson;


    @BeforeEach
    public void prepareData() {
        expectedNormalJson = "{\"key\":\"chars1\",\"type\":\"unchanged\",\"value\":[\"a\",\"b\",\"c\"]}\n"
                + "{\"key\":\"chars2\",\"type\":\"changed\",\"value1\":[\"d\",\"e\",\"f\"],\"value2\":false}\n"
                + "{\"key\":\"checked\",\"type\":\"changed\",\"value1\":false,\"value2\":true}\n"
                + "{\"key\":\"default\",\"type\":\"changed\",\"value1\":null,\"value2\":[\"value1\",\"value2\"]}\n"
                + "{\"key\":\"id\",\"type\":\"changed\",\"value1\":45,\"value2\":null}\n"
                + "{\"key\":\"key1\",\"type\":\"deleted\",\"value\":\"value1\"}\n"
                + "{\"key\":\"key2\",\"type\":\"added\",\"value\":\"value2\"}\n"
                + "{\"key\":\"numbers1\",\"type\":\"unchanged\",\"value\":[1,2,3,4]}\n"
                + "{\"key\":\"numbers2\",\"type\":\"changed\",\"value1\":[2,3,4,5],\"value2\":[22,33,44,55]}\n"
                + "{\"key\":\"numbers3\",\"type\":\"deleted\",\"value\":[3,4,5]}\n"
                + "{\"key\":\"numbers4\",\"type\":\"added\",\"value\":[4,5,6]}\n"
                + "{\"key\":\"obj1\",\"type\":\"added\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true}}\n"
                + "{\"key\":\"setting1\",\"type\":\"changed\",\"value1\":\"Some value\",\"value2\":\"Another value\"}\n"
                + "{\"key\":\"setting2\",\"type\":\"changed\",\"value1\":200,\"value2\":300}\n"
                + "{\"key\":\"setting3\",\"type\":\"changed\",\"value1\":true,\"value2\":\"none\"}";

        expectedFirstEmptyJson = "{\"key\":\"chars1\",\"type\":\"added\",\"value\":[\"a\",\"b\",\"c\"]}\n"
                    + "{\"key\":\"chars2\",\"type\":\"added\",\"value\":false}\n"
                    + "{\"key\":\"checked\",\"type\":\"added\",\"value\":true}\n"
                    + "{\"key\":\"default\",\"type\":\"added\",\"value\":[\"value1\",\"value2\"]}\n"
                    + "{\"key\":\"id\",\"type\":\"added\",\"value\":null}\n"
                    + "{\"key\":\"key2\",\"type\":\"added\",\"value\":\"value2\"}\n"
                    + "{\"key\":\"numbers1\",\"type\":\"added\",\"value\":[1,2,3,4]}\n"
                    + "{\"key\":\"numbers2\",\"type\":\"added\",\"value\":[22,33,44,55]}\n"
                    + "{\"key\":\"numbers4\",\"type\":\"added\",\"value\":[4,5,6]}\n"
                    + "{\"key\":\"obj1\",\"type\":\"added\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true}}\n"
                    + "{\"key\":\"setting1\",\"type\":\"added\",\"value\":\"Another value\"}\n"
                    + "{\"key\":\"setting2\",\"type\":\"added\",\"value\":300}\n"
                    + "{\"key\":\"setting3\",\"type\":\"added\",\"value\":\"none\"}";

        expectedSecondEmptyJson = "{\"key\":\"chars1\",\"type\":\"deleted\",\"value\":[\"a\",\"b\",\"c\"]}\n"
                                + "{\"key\":\"chars2\",\"type\":\"deleted\",\"value\":[\"d\",\"e\",\"f\"]}\n"
                                + "{\"key\":\"checked\",\"type\":\"deleted\",\"value\":false}\n"
                                + "{\"key\":\"default\",\"type\":\"deleted\",\"value\":null}\n"
                                + "{\"key\":\"id\",\"type\":\"deleted\",\"value\":45}\n"
                                + "{\"key\":\"key1\",\"type\":\"deleted\",\"value\":\"value1\"}\n"
                                + "{\"key\":\"numbers1\",\"type\":\"deleted\",\"value\":[1,2,3,4]}\n"
                                + "{\"key\":\"numbers2\",\"type\":\"deleted\",\"value\":[2,3,4,5]}\n"
                                + "{\"key\":\"numbers3\",\"type\":\"deleted\",\"value\":[3,4,5]}\n"
                                + "{\"key\":\"setting1\",\"type\":\"deleted\",\"value\":\"Some value\"}\n"
                                + "{\"key\":\"setting2\",\"type\":\"deleted\",\"value\":200}\n"
                                + "{\"key\":\"setting3\",\"type\":\"deleted\",\"value\":true}";


        expectedNormalStylish = "{\n"
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

        expectedFirstEmptyStylish = "{\n"
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

        expectedSecondEmptyStylish = "{\n"
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

        expectedNormalPlain = "Property 'chars2' was updated. From [complex value] to false\n"
                                    + "Property 'checked' was updated. From false to true\n"
                                    + "Property 'default' was updated. From null to [complex value]\n"
                                    + "Property 'id' was updated. From 45 to null\n"
                                    + "Property 'key1' was removed\n"
                                    + "Property 'key2' was added with value: 'value2'\n"
                                    + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                                    + "Property 'numbers3' was removed\n"
                                    + "Property 'numbers4' was added with value: [complex value]\n"
                                    + "Property 'obj1' was added with value: [complex value]\n"
                                    + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                                    + "Property 'setting2' was updated. From 200 to 300\n"
                                    + "Property 'setting3' was updated. From true to 'none'";


        expectedFirstEmptyPlain = "Property 'chars1' was added with value: [complex value]\n"
                                        + "Property 'chars2' was added with value: false\n"
                                        + "Property 'checked' was added with value: true\n"
                                        + "Property 'default' was added with value: [complex value]\n"
                                        + "Property 'id' was added with value: null\n"
                                        + "Property 'key2' was added with value: 'value2'\n"
                                        + "Property 'numbers1' was added with value: [complex value]\n"
                                        + "Property 'numbers2' was added with value: [complex value]\n"
                                        + "Property 'numbers4' was added with value: [complex value]\n"
                                        + "Property 'obj1' was added with value: [complex value]\n"
                                        + "Property 'setting1' was added with value: 'Another value'\n"
                                        + "Property 'setting2' was added with value: 300\n"
                                        + "Property 'setting3' was added with value: 'none'";


        expectedSecondEmptyPlain = "Property 'chars1' was removed\n"
                                        + "Property 'chars2' was removed\n"
                                        + "Property 'checked' was removed\n"
                                        + "Property 'default' was removed\n"
                                        + "Property 'id' was removed\n"
                                        + "Property 'key1' was removed\n"
                                        + "Property 'numbers1' was removed\n"
                                        + "Property 'numbers2' was removed\n"
                                        + "Property 'numbers3' was removed\n"
                                        + "Property 'setting1' was removed\n"
                                        + "Property 'setting2' was removed\n"
                                        + "Property 'setting3' was removed";
    }

    @Test
    public void testEmptyFiles() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json", "stylish"), "{\n}");

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/empty2.yml", "plain"), "");

        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json", "json"), "");

    }


    @Test
    public void testNormalJson() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json", "stylish"), expectedNormalStylish);

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
