package by.epam.javatraining.aksenov.task4.model.entity;

import java.io.Serializable;

public interface Item extends Serializable {
    String getText();

    ItemType getItemType();

    void setText(String text);
}
