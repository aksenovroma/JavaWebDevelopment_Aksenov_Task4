package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.logic.separator.TextSeparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeItem implements Item {
    public static final String SEPARATOR_FOR_PARAGRAPH;
    public static final String SEPARATOR_FOR_SENTENCE;

    static {
        SEPARATOR_FOR_PARAGRAPH = "\n\n";
        SEPARATOR_FOR_SENTENCE = " ";
    }

    private String text;
    private ItemType itemType = ItemType.TEXT;
    private List<Item> items = new ArrayList<>();

    public CompositeItem(String text) {
        this.text = text;
        parse();
    }

    public CompositeItem(String text, ItemType itemType) {
        this.text = text;
        this.itemType = itemType;
    }

    public void removeItem(Item syntaxItem) {
        items.remove(syntaxItem);
    }

    public void addItem(Item syntaxItem) {
        items.add(syntaxItem);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> get() {
        return items;
    }

    public void set(List<Item> items) {
        this.items = items;
    }

    public void parse() {
        new TextSeparator().separate(this);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public ItemType getItemType() {
        return itemType;
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
