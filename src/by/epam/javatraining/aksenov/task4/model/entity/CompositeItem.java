package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.logic.separator.TextSeparator;

import java.util.ArrayList;
import java.util.List;

public class CompositeItem implements Item {
    public static final String REGEX_FOR_PARAGRAPH;
    public static final String REGEX_FOR_SENTENCES;
    public static final String REGEX_FOR_SENTENCE_ITEMS;

    static {
        REGEX_FOR_PARAGRAPH = "\\n{2,}";
        REGEX_FOR_SENTENCES = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?|\\!|:)\\s";
        REGEX_FOR_SENTENCE_ITEMS = "(?<=[\\s])|(?<=[\\p{Punct}&&[^']])(?!\\s)|(?=[\\p{Punct}&&[^']])(?<!\\s)"
                + "|(?<=[\\s\\p{Punct}]['])(?!\\s)|(?=['][\\s\\p{Punct}])(?<!\\s)|([\\s])";
    }

    private String text;
    private ItemType itemType;
    private List<Item> items = new ArrayList<>();

    public CompositeItem(String text, ItemType itemType) {
        this.text = text;
        this.itemType = itemType;
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

    public void parse() {
        new TextSeparator().separate(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Item c : items) {
            if (c instanceof CompositeItem || c.getItemType() == ItemType.CODE_BLOCK) {
                if ((c.getItemType() == ItemType.PARAGRAPH || c.getItemType() == ItemType.CODE_BLOCK)) {
                    sb.append(c.toString()).append("\n\n");
                } else {
                    sb.append(c.toString()).append(" ");
                }
            }
            else {
                sb.append(c.toString());
            }
        }

        return sb.toString();
    }
}
