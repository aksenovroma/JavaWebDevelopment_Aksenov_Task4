package by.epam.javatraining.aksenov.task4.model.logic;

import java.util.regex.Pattern;

/**
 * @author aksenov
 * @version 1.0
 * @date 23.03.2019
 */

public class Separator {
    public static String[] separate(String string, String regex) {
        if (string != null && regex != null) {
            Pattern patternText = Pattern.compile(regex);
            return patternText.split(string);
        }
        return null;
    }
}
