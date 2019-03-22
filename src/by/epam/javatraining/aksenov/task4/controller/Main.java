package by.epam.javatraining.aksenov.task4.controller;

import by.epam.javatraining.aksenov.task4.model.noname.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.noname.ItemType;
import by.epam.javatraining.aksenov.task4.model.noname.SyntaxItem;
import by.epam.javatraining.aksenov.task4.util.DataReader;
import by.epam.javatraining.aksenov.task4.util.EmptyFileException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String file = null;
        try {
            file = DataReader.readFile("input/textExample1.txt");
        } catch (EmptyFileException e) {
            e.printStackTrace();
        }

        CompositeItem text = new CompositeItem(file, ItemType.TEXT);

        System.out.println(text);
    }
}
