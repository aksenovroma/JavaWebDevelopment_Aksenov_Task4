package by.epam.javatraining.aksenov.task4.controller;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.logic.TextHandler;
import by.epam.javatraining.aksenov.task4.util.DataReader;
import by.epam.javatraining.aksenov.task4.util.PrinterCreator;
import by.epam.javatraining.aksenov.task4.util.UserPrinter;
import by.epam.javatraining.aksenov.task4.util.exception.EmptyFileException;
import by.epam.javatraining.aksenov.task4.view.Printable;
import by.epam.javatraining.aksenov.task4.view.PrinterType;
import org.apache.log4j.Logger;


/**
 * C using the OOP methodology and the architectural design template
 * MVC needs to be designed and implemented in a programming language.
 * Java software system that analyzes and parses text from
 *
 * programming tutorial as well as provide additional
 *
 * operations in accordance with its own variant of the task
 * brothers alone, but not less than two).
 *
 * @author aksenov
 * @version 1.0
 * @date 25.03.2019
 */

public class Main {
    private static Logger log = Logger.getRootLogger();

    private static final String INPUT_FILE = "input/textExample1.txt";
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

        CompositeItem textCopy1 = text.clone();
        CompositeItem textCopy2 = text.clone();
        CompositeItem textCopy3 = text.clone();

        printer.print(text);

        TextHandler.swapFirstLastWordInAllSentence(textCopy1);
        printer.print(textCopy1);

        TextHandler.removeWordsWithFirstVowel(textCopy2, 5);
        printer.print(textCopy2);

        TextHandler.reverseAllSentences(textCopy3);
        printer.print(textCopy3);

    }
}
