package by.epam.javatraining.aksenov.task4.controller;

import by.epam.javatraining.aksenov.task4.model.entity.Paragraph;
import by.epam.javatraining.aksenov.task4.model.entity.Sentence;
import by.epam.javatraining.aksenov.task4.model.entity.SyntaxItem;
import by.epam.javatraining.aksenov.task4.model.entity.Text;
import by.epam.javatraining.aksenov.task4.util.DataReader;
import by.epam.javatraining.aksenov.task4.util.EmptyFileException;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String file = null;
        try {
            file = DataReader.readFile("input/textExample.txt");
        } catch (EmptyFileException e) {
            e.printStackTrace();
        }

        Text text = new Text(file);
        for (String str : text.getCodeBloks()) {
            System.out.println(str);
        }

    }
}
