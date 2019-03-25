package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.exception.logic.WrongArgumentException;

import java.io.Serializable;

public interface Item extends Serializable {
    String getText();

    ItemType getItemType();

    void setText(String text) throws WrongArgumentException;
}
