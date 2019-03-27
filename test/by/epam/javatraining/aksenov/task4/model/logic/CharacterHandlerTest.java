package by.epam.javatraining.aksenov.task4.model.logic;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CharacterHandlerTest {

    @Test
    public void testIsVowel() {
        Random random = new Random();

        String vowel = "AEIOUaeiou";
        int index = random.nextInt(vowel.length());
        boolean actual = CharacterHandler.isVowel(vowel.charAt(index));

        assertTrue(actual);
    }

    @Test
    public void testIsVowelNegative() {
        Random random = new Random();

        String consonant = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz";
        int index = random.nextInt(consonant.length());
        boolean actual = CharacterHandler.isVowel(consonant.charAt(index));

        assertFalse(actual);
    }
}
