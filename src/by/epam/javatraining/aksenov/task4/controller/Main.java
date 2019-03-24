package by.epam.javatraining.aksenov.task4.controller;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.logic.TextHandler;
import by.epam.javatraining.aksenov.task4.util.DataReader;
import by.epam.javatraining.aksenov.task4.util.PrinterCreator;
import by.epam.javatraining.aksenov.task4.util.UserPrinter;
import by.epam.javatraining.aksenov.task4.util.exception.EmptyFileException;
import by.epam.javatraining.aksenov.task4.view.Printable;
import by.epam.javatraining.aksenov.task4.view.PrinterType;
import org.apache.log4j.Logger;

public class Main {
    private static Logger log = Logger.getRootLogger();

    private static final String INPUT_FILE = "input/textExample2.txt";
    private static final String OUTPUT_FILE = "output/outputFile.txt";

    public static void main(String[] args) {
        PrinterType printerType = UserPrinter.select(OUTPUT_FILE);
        Printable printer = PrinterCreator.create(printerType);

        String textFromFile = null;

        try {
            textFromFile = DataReader.readFile(INPUT_FILE);
        } catch (EmptyFileException e) {
            log.error(e);
        }

        CompositeItem text = new CompositeItem(textFromFile);
        CompositeItem copyText = (CompositeItem) TextHandler.clone(text);

        TextHandler.removeWordsWithFirstVowel(copyText, 5);
        printer.print(copyText);
        System.out.println("======================================");
        printer.print(text);

    }
}
