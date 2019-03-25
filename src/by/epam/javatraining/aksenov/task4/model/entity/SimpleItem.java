package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.exception.logic.SimpleItemWrongTextException;

import java.util.Objects;

public class SimpleItem implements Item {
    public static final String DEFAULT_TEXT = "";
    public static final ItemType DEFAULT_ITEM_TYPE = ItemType.SPACE;

    private String text = DEFAULT_TEXT;
    private ItemType itemType = DEFAULT_ITEM_TYPE;

    public SimpleItem(String text, ItemType itemType) {
        if (text != null) {
            this.text = text;
        }
        if (itemType == ItemType.SPACE
                || itemType == ItemType.WORD
                || itemType == ItemType.PUNCTUATION
                || itemType == ItemType.CODE_BLOCK) {
            this.itemType = itemType;
        }
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
    public void setText(String text) throws SimpleItemWrongTextException {
        if (text == null) {
            throw new SimpleItemWrongTextException();
        }
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleItem that = (SimpleItem) o;

        return Objects.equals(text, that.text) &&
                itemType == that.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, itemType);
    }

    @Override
    public String toString() {
        return text;
    }
}
