package by.epam.javatraining.aksenov.task4.model.noname;

import by.epam.javatraining.aksenov.task4.model.logic.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public enum ItemType {
    TEXT, PARAGRAPH, SENTENCE;

    public static final String REGEX_FOR_PUNCTUATION = "\\p{Punct}";

    public List<SyntaxItem> separateText(String text, String regex) {
        String[] paragraphsArr = Parser.parse(text, regex);
        List<SyntaxItem> paragraphsList = new ArrayList<>();

        for (String paragraph : paragraphsArr) {
            CompositeItem par = new CompositeItem(paragraph, ItemType.PARAGRAPH);
            paragraphsList.add(par);
        }

        return paragraphsList;
    }

    public List<SyntaxItem> separateParagraph(String text, String regex) {
        String[] sentenceArr = Parser.parse(text, regex);
        List<SyntaxItem> sentenceList = new ArrayList<>();

        for (String sent : sentenceArr) {
            CompositeItem sentence = new CompositeItem(sent, ItemType.SENTENCE);
            sentenceList.add(sentence);
        }

        return sentenceList;
    }

    public List<SyntaxItem> separateSentence(String text, String regex) {
        String[] sentenceItemArr = Parser.parse(text, regex);
        List<SyntaxItem> sentenceItemList = new ArrayList<>();

        for (String sentItem : sentenceItemArr) {
            if (Pattern.matches(REGEX_FOR_PUNCTUATION, sentItem)){
                SyntaxItem punct = new Punctuation(sentItem);
                sentenceItemList.add(punct);
            }
            else {
                SyntaxItem word = new Word(sentItem);
                sentenceItemList.add(word);
            }
        }

        return sentenceItemList;
    }
}
