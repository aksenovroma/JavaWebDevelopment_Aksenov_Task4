package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.exception.logic.CompositeItemWrongListException;
import by.epam.javatraining.aksenov.task4.model.exception.logic.CompositeItemWrongTextException;
import by.epam.javatraining.aksenov.task4.model.logic.separator.TextSeparator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author aksenov
 * @version 2.0
 * @date 23.03.2019
 */

public class CompositeItem implements Item {
    public static final String SEPARATOR_FOR_PARAGRAPH;
    public static final String SEPARATOR_FOR_SENTENCE;
    public static final String DEFAULT_TEXT;
    public static final ItemType DEFAULT_ITEM_TYPE;

    private static Logger log = Logger.getRootLogger();

    static {
        SEPARATOR_FOR_PARAGRAPH = "\n\n";
        SEPARATOR_FOR_SENTENCE = " ";
        DEFAULT_TEXT = "";
        DEFAULT_ITEM_TYPE = ItemType.TEXT;
    }

    private String text = DEFAULT_TEXT;
    private ItemType itemType = DEFAULT_ITEM_TYPE;
    private List<Item> items = new ArrayList<>();

    public CompositeItem(String text) {
        if (text != null) {
            this.text = text;
        }
        parse();
    }

    public CompositeItem(String text, ItemType itemType) {
        if (text != null) {
            this.text = text;
        }
        if (itemType == ItemType.TEXT
                || itemType == ItemType.PARAGRAPH
                || itemType == ItemType.SENTENCE) {
            this.itemType = itemType;
        }
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public List<Item> get() {
        return items;
    }

    public void set(List<Item> items) throws CompositeItemWrongListException {
        if (items == null) {
            throw new CompositeItemWrongListException();
        }
        this.items = items;
    }

    public void parse() {
        new TextSeparator().separate(this);
    }

    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) throws CompositeItemWrongTextException {
        if (text == null) {
            throw new CompositeItemWrongTextException();
        }
        this.text = text;
    }

    public CompositeItem clone() {
        CompositeItem copyItem = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream ous = new ObjectOutputStream(baos)) {
            ous.writeObject(this);
        } catch (IOException e) {
            log.error(e);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            copyItem = (CompositeItem) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error(e);
        }

        return copyItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompositeItem that = (CompositeItem) o;

        return Objects.equals(text, that.text) &&
                itemType == that.itemType &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, itemType, items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Item c : items) {
            if (c instanceof CompositeItem || c.getItemType() == ItemType.CODE_BLOCK) {
                if ((c.getItemType() == ItemType.PARAGRAPH || c.getItemType() == ItemType.CODE_BLOCK)) {
                    sb.append(c.toString()).append(SEPARATOR_FOR_PARAGRAPH);
                } else {
                    sb.append(c.toString()).append(SEPARATOR_FOR_SENTENCE);
                }
            } else {
                sb.append(c.toString());
            }
        }

        return sb.toString();
    }
}
