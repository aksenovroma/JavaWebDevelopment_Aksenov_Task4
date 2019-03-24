package by.epam.javatraining.aksenov.task4.model.entity;

public class SimpleItem implements Item {
    private String text;
    private ItemType itemType;

    public SimpleItem(String text, ItemType itemType) {
        this.text = text;
        this.itemType = itemType;
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
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
