package by.epam.javatraining.aksenov.task4.model.logic;

import java.util.regex.Pattern;

/**
 * @author aksenov
 * @version 1.0
 * @date 23.03.2019
 */

public class Parser {
    public static String[] parse(String string, String regex) {
        if (string != null && regex != null) {
            Pattern patternText = Pattern.compile(regex);
            return patternText.split(string);
        }
        return null;
    }
}
