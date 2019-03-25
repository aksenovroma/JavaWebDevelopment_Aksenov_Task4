package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.exception.logic.SimpleItemWrongTextException;
import by.epam.javatraining.aksenov.task4.model.exception.logic.WrongArgumentException;
import by.epam.javatraining.aksenov.task4.model.logic.TextHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SimpleItemTest {
    private SimpleItem simpleItem1;

    @BeforeMethod
    public void setSimpleItem1() {
        simpleItem1 = new SimpleItem("Roman", ItemType.WORD);
    }

    @Test
    public void testConstructor() {
        String text = "Roman";
        ItemType itemType = ItemType.WORD;

        SimpleItem simpleItem = new SimpleItem(text, itemType);

        String expectedText = text;
        ItemType expectedItemType = itemType;

        String actualText = simpleItem.getText();
        ItemType actualItemType = simpleItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongText() {
        String text = null;
        ItemType itemType = ItemType.WORD;

        SimpleItem simpleItem = new SimpleItem(text, itemType);

        String expectedText = "";
        ItemType expectedItemType = itemType;

        String actualText = simpleItem.getText();
        ItemType actualItemType = simpleItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongItemType() {
        String text = "Roman";
        ItemType itemType = ItemType.TEXT;

        SimpleItem simpleItem = new SimpleItem(text, itemType);

        String expectedText = text;
        ItemType expectedItemType = ItemType.SPACE;

        String actualText = simpleItem.getText();
        ItemType actualItemType = simpleItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongTextAndItemType() {
        String text = null;
        ItemType itemType = ItemType.TEXT;

        SimpleItem simpleItem = new SimpleItem(text, itemType);

        String expectedText = "";
        ItemType expectedItemType = ItemType.SPACE;

        String actualText = simpleItem.getText();
        ItemType actualItemType = simpleItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testSetText() {
        String expected = simpleItem1.getText();
        SimpleItem simpleItem = new SimpleItem("", ItemType.WORD);
        try {
            simpleItem.setText(expected);
        } catch (SimpleItemWrongTextException e) {
            e.printStackTrace();
        }
        String actual = simpleItem.getText();

        assertEquals(expected, actual);
    }

    @Test (expectedExceptions = SimpleItemWrongTextException.class)
    public void testSetWrongText() throws SimpleItemWrongTextException {
        simpleItem1.setText(null);
    }

    @Test
    public void testEquals() {
        Item simpleItem = TextHandler.clone(simpleItem1);

        boolean actual = simpleItem.equals(simpleItem1);

        assertTrue(actual);
    }

    @Test
    public void testEqualsDifferentText() {
        Item simpleItem = TextHandler.clone(simpleItem1);
        try {
            simpleItem.setText("");
        } catch (WrongArgumentException e) {
            e.printStackTrace();
        }

        boolean actual = simpleItem.equals(simpleItem1);

        assertFalse(actual);
    }

    @Test
    public void testEqualsDifferentItemType() {
        Item simpleItem = new SimpleItem(simpleItem1.getText(), ItemType.SPACE);

        boolean actual = simpleItem.equals(simpleItem1);

        assertFalse(actual);
    }

    @Test
    public void testEqualsNullPtr() {
        Item compositeItem = TextHandler.clone(simpleItem1);

        boolean actual = compositeItem.equals(null);

        assertFalse(actual);
    }

    @Test
    public void testEqualsSameObjects() {
        Item simpleItem = simpleItem1;

        boolean actual = simpleItem.equals(simpleItem1);

        assertTrue(actual);
    }

    @Test
    public void testEqualsOtherClass() {
        Object o = new Object();

        boolean actual = simpleItem1.equals(o);

        assertFalse(actual);
    }

    @Test
    public void testHashCode() {
        Item simpleItem = TextHandler.clone(simpleItem1);

        boolean actual = (simpleItem.hashCode() == simpleItem1.hashCode());

        assertTrue(actual);
    }

    @Test
    public void testHashCodeSameObjects() {
        Item simpleItem = simpleItem1;

        boolean actual = (simpleItem.hashCode() == simpleItem1.hashCode());

        assertTrue(actual);
    }

    @Test
    public void testHashCodeDifferentText() {
        Item simpleItem = TextHandler.clone(simpleItem1);
        try {
            simpleItem.setText("");
        } catch (WrongArgumentException e) {
            e.printStackTrace();
        }

        boolean actual = (simpleItem.hashCode() == simpleItem1.hashCode());

        assertFalse(actual);
    }

    @Test
    public void testHashCodeDifferentItemType() {
        Item simpleItem = new CompositeItem(simpleItem1.getText(), ItemType.PUNCTUATION);

        boolean actual = (simpleItem.hashCode() == simpleItem1.hashCode());

        assertFalse(actual);
    }

    @Test
    public void testToString() {
        String expected = "Roman";
        String actual = simpleItem1.toString();

        assertEquals(expected, actual);
    }

}

