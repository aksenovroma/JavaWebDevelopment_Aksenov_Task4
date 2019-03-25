package by.epam.javatraining.aksenov.task4.model.logic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParserTest {

    @Test
    public void testParse() {
        String string = "Roma.Aksenov";
        String regex = "\\.";

        String[] expected = {"Roma", "Aksenov"};
        String[] actual = Parser.parse(string, regex);

        assertEquals(expected, actual);
    }

    @Test
    public void testParseNullString() {
        String string = null;
        String regex = "\\.";

        String[] expected = null;
        String[] actual = Parser.parse(string, regex);

        assertEquals(expected, actual);
    }

    @Test
    public void testParseNullRegex() {
        String string = "Roma.Aksenov";
        String regex = null;

        String[] expected = null;
        String[] actual = Parser.parse(string, regex);

        assertEquals(expected, actual);
    }
}
