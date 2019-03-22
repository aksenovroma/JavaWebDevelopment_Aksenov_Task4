package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.util.Parser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public enum ItemType {
    TEXT, PARAGRAPH, SENTENCE, WORD, PUNCTUATION;

    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_PUNCTUATION = "\\p{Punct}";

    public List<CompositeItem> separateText(String text, String regex) {
        String[] paragraphsArr = Parser.parse(text, regex);
        List<CompositeItem> paragraphsList = new ArrayList<>();

        for (String paragraph : paragraphsArr) {
            CompositeItem par = new CompositeItem(paragraph, ItemType.PARAGRAPH);
            log.trace("paragraph has been created");
            paragraphsList.add(par);
        }

        return paragraphsList;
    }

    public List<CompositeItem> separateParagraph(String text, String regex) {
        String[] sentenceArr = Parser.parse(text, regex);
        List<CompositeItem> sentenceList = new ArrayList<>();

        for (String sent : sentenceArr) {
            CompositeItem sentence = new CompositeItem(sent, ItemType.SENTENCE);
            log.trace("sentence has been created");
            sentenceList.add(sentence);
        }

        return sentenceList;
    }

    public List<CompositeItem> separateSentence(String text, String regex) {
        String[] sentenceItemArr = Parser.parse(text, regex);
        List<CompositeItem> sentenceItemList = new ArrayList<>();

        for (String sentItem : sentenceItemArr) {
            if (Pattern.matches(REGEX_FOR_PUNCTUATION, sentItem)){
                CompositeItem punct = new CompositeItem(sentItem, ItemType.PUNCTUATION);
                log.trace("punctuation has been created");
                sentenceItemList.add(punct);
            }
            else {
                CompositeItem word = new CompositeItem(sentItem, ItemType.WORD);
                log.trace("word has been created");
                sentenceItemList.add(word);
            }
        }

        return sentenceItemList;
    }
}
