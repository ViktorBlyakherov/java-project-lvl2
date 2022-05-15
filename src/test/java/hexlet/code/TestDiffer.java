package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {
    @BeforeEach
    public void prepareData() {

    }

    @Test
    public void testEmptyFiles() throws IOException {
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/empty2.json"), "{\n}");

        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/empty2.yml"), "{\n}");
    }

    @Test
    public void testNormalJson() throws IOException {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/normal2.json"), expected);
    }

    @Test
    public void testFirstEmptyJson() throws IOException {
        String expected = "{\n"
            + "- follow: false\n"
            + "- host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "}";
        assertEquals(new Differ().generate("./src/test/resources/normal1.json",
                "./src/test/resources/empty2.json"), expected);
    }

    @Test
    public void testSecondEmptyJson() throws IOException {
        String expected = "{\n"
            + "+ host: hexlet.io\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";
        assertEquals(new Differ().generate("./src/test/resources/empty1.json",
                "./src/test/resources/normal2.json"), expected);
    }

    @Test
    public void testNormalYml() throws IOException {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/normal2.yml"), expected);
    }

    @Test
    public void testFirstEmptyYml() throws IOException {
        String expected = "{\n"
                + "- follow: false\n"
                + "- host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "}";
        assertEquals(new Differ().generate("./src/test/resources/normal1.yml",
                "./src/test/resources/empty2.yml"), expected);
    }

    @Test
    public void testSecondEmptyYml() throws IOException {
        String expected = "{\n"
                + "+ host: hexlet.io\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        assertEquals(new Differ().generate("./src/test/resources/empty1.yml",
                "./src/test/resources/normal2.yml"), expected);
    }

}
