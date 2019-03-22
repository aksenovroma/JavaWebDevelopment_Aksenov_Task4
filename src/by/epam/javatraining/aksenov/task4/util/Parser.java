package by.epam.javatraining.aksenov.task4.util;

import java.util.regex.Pattern;

public class Parser {
    public static String[] parse(String string, String regex) {
        Pattern patternText = Pattern.compile(regex);
        return patternText.split(string);
    }

    public static boolean isCodeBlock(String text) {
        return text.contains("(") && text.contains(";") && text.contains(")");
    }
}
