package by.epam.javatraining.aksenov.task4.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CompositeItem {
    public static final String REGEX_FOR_PARAGRAPH;
    public static final String REGEX_FOR_SENTENCES;
    public static final String REGEX_FOR_SENTENCE_ITEMS;

    static {
        REGEX_FOR_PARAGRAPH = "\\n+";
        REGEX_FOR_SENTENCES = "(?<=[a-z])\\.\\s+";
        REGEX_FOR_SENTENCE_ITEMS = "\\s+|(?<=[\\p{Punct}&&[^']])(?!\\s)|(?=[\\p{Punct}&&[^']])(?<!\\s)"
                + "|(?<=[\\s\\p{Punct}]['])(?!\\s)|(?=['][\\s\\p{Punct}])(?<!\\s)";
    }

    private String text;
    private ItemType itemType;
    private List<CompositeItem> items = new ArrayList<>();

    public CompositeItem(String text, ItemType itemType) {
        this.text = text;
        this.itemType = itemType;
        parse();
    }

    public String getText() {
        return text;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void removeItem(CompositeItem syntaxItem) {
        items.remove(syntaxItem);
    }

    public void addItem(CompositeItem syntaxItem) {
        items.add(syntaxItem);
    }

    public CompositeItem getItem(int index) {
        return items.get(index);
    }

    public List<CompositeItem> get() {
        return items;
    }

    public void parse() {
        switch (itemType) {
            case TEXT: {
                items = itemType.separateText(text, REGEX_FOR_PARAGRAPH);
            }
            case PARAGRAPH: {
                items = itemType.separateParagraph(text, REGEX_FOR_SENTENCES);
            }
            case SENTENCE: {
                items = itemType.separateSentence(text, REGEX_FOR_SENTENCE_ITEMS);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (CompositeItem sI : items) {
            if (sI.getItemType() == ItemType.WORD) {
                sb.append(sI.getText()).append(" ");
            } else if (sI.getItemType() == ItemType.PUNCTUATION) {
                sb.append(sI.getText());
            }
        }

        return sb.toString();
    }
}
