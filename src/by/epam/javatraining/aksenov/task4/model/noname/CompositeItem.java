package by.epam.javatraining.aksenov.task4.model.noname;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeItem implements SyntaxItem{
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
    private List<SyntaxItem> items = new ArrayList<>();

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

    public void removeItem(SyntaxItem syntaxItem) {
        items.remove(syntaxItem);
    }

    public void addItem(SyntaxItem syntaxItem) {
        items.add(syntaxItem);
    }

    public SyntaxItem getItem(int index) {
        return items.get(index);
    }

    public List<SyntaxItem> get() {
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

        for (SyntaxItem sI : items) {
            sb.append(sI.toString());
        }

        return sb.toString();
    }
}
