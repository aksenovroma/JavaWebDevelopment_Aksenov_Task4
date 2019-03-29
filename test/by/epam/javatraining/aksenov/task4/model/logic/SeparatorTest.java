package by.epam.javatraining.aksenov.task4.model.logic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SeparatorTest {

    @Test
    public void testSeparate() {
        String string = "Roma.Aksenov";
        String regex = "\\.";

        String[] expected = {"Roma", "Aksenov"};
        String[] actual = Separator.separate(string, regex);

        assertEquals(expected, actual);
    }

    @Test
    public void testSeparateNullString() {
        String string = null;
        String regex = "\\.";

        String[] expected = null;
        String[] actual = Separator.separate(string, regex);

        assertEquals(expected, actual);
    }

    @Test
    public void testSeparateNullRegex() {
        String string = "Roma.Aksenov";
        String regex = null;

        String[] expected = null;
        String[] actual = Separator.separate(string, regex);

        assertEquals(expected, actual);
    }
}
