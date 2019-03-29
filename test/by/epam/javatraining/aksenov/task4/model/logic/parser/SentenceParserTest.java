package by.epam.javatraining.aksenov.task4.model.logic.parser;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SentenceParserTest {

    @Test
    public void testSeparator() {
        String text1 = "Autoboxing,    is the automatic conversion.\n\n";
        CompositeItem text = new CompositeItem(text1);
        text.parse();
        StringBuilder sb = new StringBuilder();

        for (Item cI : text.get()) {
            sb.append(cI.getText()).append("\n\n");
        }

        String expected = text1;
        String actual = sb.toString();

        assertEquals(expected, actual);
    }
}
