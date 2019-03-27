package by.epam.javatraining.aksenov.task4.model.logic;

public class CharacterHandler {
    public static final String VOWELS = "AEIOUaeiou";

    public static boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }
}
