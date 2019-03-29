package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.exception.logic.CompositeItemWrongListException;
import by.epam.javatraining.aksenov.task4.model.exception.logic.CompositeItemWrongTextException;
import by.epam.javatraining.aksenov.task4.model.exception.logic.WrongArgumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static by.epam.javatraining.aksenov.task4.model.logic.TextHandlerData.text1;
import static org.testng.Assert.*;

public class CompositeItemTest {
    private CompositeItem compositeItem1;

    @BeforeMethod
    public void setCompositeItem1() {
        compositeItem1 = new CompositeItem("Roman Aksenov");
    }

    @Test
    public void testTextConstructor() {
        String text = "Roman Aksenov";

        CompositeItem compositeItem = new CompositeItem(text);

        String expected = text;
        String actual = compositeItem.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void testTextConstructorWrongText() {
        String text = null;

        CompositeItem compositeItem = new CompositeItem(text);

        String expected = "";
        String actual = compositeItem.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void testConstructor() {
        String text = "Roman Aksenov";
        ItemType itemType = ItemType.TEXT;

        CompositeItem compositeItem = new CompositeItem(text, itemType);

        String expectedText = text;
        ItemType expectedItemType = itemType;

        String actualText = compositeItem.getText();
        ItemType actualItemType = compositeItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongText() {
        String text = null;
        ItemType itemType = ItemType.TEXT;

        CompositeItem compositeItem = new CompositeItem(text, itemType);

        String expectedText = "";
        ItemType expectedItemType = itemType;

        String actualText = compositeItem.getText();
        ItemType actualItemType = compositeItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongItemType() {
        String text = "Roman Aksenov";
        ItemType itemType = ItemType.WORD;

        CompositeItem compositeItem = new CompositeItem(text, itemType);

        String expectedText = text;
        ItemType expectedItemType = ItemType.TEXT;

        String actualText = compositeItem.getText();
        ItemType actualItemType = compositeItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testConstructorWrongTextAndItemType() {
        String text = null;
        ItemType itemType = ItemType.WORD;

        CompositeItem compositeItem = new CompositeItem(text, itemType);

        String expectedText = "";
        ItemType expectedItemType = ItemType.TEXT;

        String actualText = compositeItem.getText();
        ItemType actualItemType = compositeItem.getItemType();

        assertEquals(expectedText, actualText);
        assertEquals(expectedItemType, actualItemType);
    }

    @Test
    public void testGetItem() {
        compositeItem1.parse();
        Item expected = compositeItem1.get().get(0);
        Item actual = compositeItem1.getItem(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetItemNegativeIndex() {
        Item expected = null;
        Item actual = compositeItem1.getItem(-1);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetItemLargeIndex() {
        Item expected = null;
        Item actual = compositeItem1.getItem(10);

        assertEquals(expected, actual);
    }

    @Test
    public void testSet() {
        List<Item> expected = compositeItem1.get();
        CompositeItem compositeItem = new CompositeItem("");
        try {
            compositeItem.set(expected);
        } catch (CompositeItemWrongListException e) {
            e.printStackTrace();
        }
        List<Item> actual = compositeItem.get();

        assertEquals(expected, actual);
    }

    @Test (expectedExceptions = CompositeItemWrongListException.class)
    public void testSetWrongItems() throws CompositeItemWrongListException {
        compositeItem1.set(null);
    }

    @Test
    public void testSetText() {
        String expected = compositeItem1.getText();
        CompositeItem compositeItem = new CompositeItem("");
        try {
            compositeItem.setText(expected);
        } catch (CompositeItemWrongTextException e) {
            e.printStackTrace();
        }
        String actual = compositeItem.getText();

        assertEquals(expected, actual);
    }

    @Test (expectedExceptions = CompositeItemWrongTextException.class)
    public void testSetWrongText() throws CompositeItemWrongTextException {
        compositeItem1.setText(null);
    }

    @Test
    public void testClone() {
        CompositeItem expected = new CompositeItem(text1);
        CompositeItem actual = expected.clone();

        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        Item compositeItem = compositeItem1.clone();

        boolean actual = compositeItem.equals(compositeItem1);

        assertTrue(actual);
    }

    @Test
    public void testEqualsDifferentText() {
        Item compositeItem = compositeItem1.clone();
        try {
            compositeItem.setText("");
        } catch (WrongArgumentException e) {
            e.printStackTrace();
        }

        boolean actual = compositeItem.equals(compositeItem1);

        assertFalse(actual);
    }

    @Test
    public void testEqualsDifferentItemType() {
        Item compositeItem = new CompositeItem(compositeItem1.getText(), ItemType.PARAGRAPH);

        boolean actual = compositeItem.equals(compositeItem1);

        assertFalse(actual);
    }

    @Test
    public void testEqualsNullPtr() {
        Item compositeItem = compositeItem1.clone();

        boolean actual = compositeItem.equals(null);

        assertFalse(actual);
    }

    @Test
    public void testEqualsSameObjects() {
        Item compositeItem = compositeItem1;

        boolean actual = compositeItem.equals(compositeItem1);

        assertTrue(actual);
    }

    @Test
    public void testEqualsOtherClass() {
        Object o = new Object();

        boolean actual = compositeItem1.equals(o);

        assertFalse(actual);
    }

    @Test
    public void testHashCode() {
        Item compositeItem = compositeItem1.clone();

        boolean actual = (compositeItem.hashCode() == compositeItem1.hashCode());

        assertTrue(actual);
    }

    @Test
    public void testHashCodeSameObjects() {
        Item compositeItem = compositeItem1;

        boolean actual = (compositeItem.hashCode() == compositeItem1.hashCode());

        assertTrue(actual);
    }

    @Test
    public void testHashCodeDifferentText() {
        Item compositeItem = compositeItem1.clone();
        try {
            compositeItem.setText("");
        } catch (WrongArgumentException e) {
            e.printStackTrace();
        }

        boolean actual = (compositeItem.hashCode() == compositeItem1.hashCode());

        assertFalse(actual);
    }

    @Test
    public void testHashCodeDifferentItemType() {
        Item compositeItem = new CompositeItem(compositeItem1.getText(), ItemType.PARAGRAPH);

        boolean actual = (compositeItem.hashCode() == compositeItem1.hashCode());

        assertFalse(actual);
    }

    @Test
    public void testToString() {
        String expected = "    Autoboxing and Unboxing. \n" +
                "Autoboxing is the automatic conversion.\n" +
                "Character ch = 'a';\n\n" +
                "The rest of the, examples in this section use generics ! If you. \n\n";
        CompositeItem text = new CompositeItem(expected);
        text.parse();
        String actual = text.toString();

        assertEquals(expected, actual);
    }
}
