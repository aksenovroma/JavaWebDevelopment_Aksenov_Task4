package by.epam.javatraining.aksenov.task4.model.entity;

import java.util.Objects;

public class SimpleItem implements Item {
    private String text = "";
    private ItemType itemType = ItemType.SPACE;

    public SimpleItem(String text, ItemType itemType) {
        if (text != null) {
            this.text = text;
        }
        if (itemType == ItemType.SPACE || itemType == ItemType.WORD || itemType == ItemType.PUNCTUATION) {
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
    public void setText(String text) {
        if (text == null) {

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
