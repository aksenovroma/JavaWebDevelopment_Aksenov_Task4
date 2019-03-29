package by.epam.javatraining.aksenov.task4.model.logic;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static by.epam.javatraining.aksenov.task4.model.logic.TextHandlerData.*;

public class TextHandlerTest {
    @Test
    public void testIndexOfFirstWord() {
        int expected = 0;
        CompositeItem text = new CompositeItem(text2);
        text.parse();
        CompositeItem paragraph = (CompositeItem) text.get().get(0);
        CompositeItem sentence = (CompositeItem) paragraph.get().get(0);
        int actual = TextHandler.indexOfFirstWord(sentence);

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOfFirstWordNullArgument() {
        int expected = -1;
        int actual = TextHandler.indexOfFirstWord(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOfFirstWordTextWithoutWords() {
        int expected = -1;
        CompositeItem text = new CompositeItem(text3);
        text.parse();
        CompositeItem paragraph = (CompositeItem) text.get().get(0);
        CompositeItem sentence = (CompositeItem) paragraph.get().get(0);
        int actual = TextHandler.indexOfFirstWord(sentence);

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOfLastWord() {
        int expected = 8;
        CompositeItem text = new CompositeItem(text2);
        text.parse();
        CompositeItem paragraph = (CompositeItem) text.get().get(0);
        CompositeItem sentence = (CompositeItem) paragraph.get().get(0);
        int actual = TextHandler.indexOfLastWord(sentence);

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOfLastWordNullArgument() {
        int expected = -1;
        int actual = TextHandler.indexOfLastWord(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOfLastWordTextWithoutWords() {
        int expected = -1;
        CompositeItem text = new CompositeItem(text3);
        text.parse();
        CompositeItem paragraph = (CompositeItem) text.get().get(0);
        CompositeItem sentence = (CompositeItem) paragraph.get().get(0);
        int actual = TextHandler.indexOfLastWord(sentence);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsSuitableWord() {
        Item word = new SimpleItem("Aksenov", ItemType.WORD);
        int length = 7;

        boolean actual = TextHandler.isSuitableWord(word, length);

        assertTrue(actual);
    }

    @Test
    public void testIsSuitableWordWrongLength() {
        Item word = new SimpleItem("Aksenov", ItemType.WORD);
        int length = 8;

        boolean actual = TextHandler.isSuitableWord(word, length);

        assertFalse(actual);
    }

    @Test
    public void testIsSuitableWordWrongWord() {
        Item word = new SimpleItem("Roman", ItemType.WORD);
        int length = 5;

        boolean actual = TextHandler.isSuitableWord(word, length);

        assertFalse(actual);
    }

    @Test
    public void testIsSuitableWordNullArgument() {
        Item word = null;
        int length = 5;

        boolean actual = TextHandler.isSuitableWord(word, length);

        assertFalse(actual);
    }

    @Test
    public void testIsCodeBlock() {
        String codeBlock = "List<Integer> li = new ArrayList<>();\n" +
                "for (int i = 1; i < 50; i += 2)\n" +
                "li.add(Integer.valueOf(i));";

        boolean actual = TextHandler.isCodeBlock(codeBlock);

        assertTrue(actual);
    }

    @Test
    public void testIsCodeBlockNegative() {
        String notCodeBlock = "absolute value of -8 = 8\n" +
                "pi = 3.1416";

        boolean actual = TextHandler.isCodeBlock(notCodeBlock);

        assertFalse(actual);
    }

    @Test
    public void testIsCodeBlockNullArgument() {
        String notCodeBlock = null;

        boolean actual = TextHandler.isCodeBlock(notCodeBlock);

        assertFalse(actual);
    }

    @Test
    public void testSwapFirstLastWordInAllSentence() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.swapFirstLastWordInAllSentence(text);

        String expected = text5;
        String actual = text.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testSwapFirstLastWordInAllSentenceNullArgument() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.swapFirstLastWordInAllSentence(null);

        String expected = text4;
        String actual = text.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveWordsWithFirstVowel() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.removeWordsWithFirstVowel(text, 9);

        String expected = text6;
        String actual = text.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveWordsWithFirstNullArgument() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.removeWordsWithFirstVowel(null, 9);

        String expected = text7;
        String actual = text.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveWordsWithFirstWrongLength() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.removeWordsWithFirstVowel(text, -9);

        String expected = text7;
        String actual = text.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testReverseAllSentences() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.reverseAllSentences(text);

        String expected = text8;
        String actual = text.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testReverseAllSentencesNullArgument() {
        CompositeItem text = new CompositeItem(text4);
        text.parse();
        TextHandler.reverseAllSentences(null);

        String expected = text7;
        String actual = text.toString();

        assertEquals(expected, actual);
    }
}
