package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.logic.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Sentence implements SyntaxItem{
    public static final String REGEX_FOR_SENTENCE;
    public static final String REGEX_FOR_PUNCTUATION;

    static {
        REGEX_FOR_SENTENCE = "\\s+|(?<=[\\p{Punct}&&[^']])(?!\\s)|(?=[\\p{Punct}&&[^']])(?<!\\s)"
                + "|(?<=[\\s\\p{Punct}]['])(?!\\s)|(?=['][\\s\\p{Punct}])(?<!\\s)";
        REGEX_FOR_PUNCTUATION = "\\p{Punct}";
    }
    private String sentence;
    private List<SyntaxItem> sentenceItems = new ArrayList<>();

    public Sentence() {

    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<SyntaxItem> getSentenceItems() {
        return sentenceItems;
    }

    public void setSentenceItems(List<SyntaxItem> sentenceItems) {
        this.sentenceItems = sentenceItems;
    }

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public void separate() {
        String[] sentItems = Parser.parse(sentence, REGEX_FOR_SENTENCE);

        for (String sentItem : sentItems) {
            if (Pattern.matches(REGEX_FOR_PUNCTUATION, sentItem)){
                Punctuation punct = new Punctuation(sentItem);
                this.sentenceItems.add(punct);
            }
            else {
                Word word = new Word(sentItem);
                this.sentenceItems.add(word);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<SyntaxItem> it = sentenceItems.iterator();

        for (SyntaxItem synItem : sentenceItems) {
            sb.append(synItem);
            if (it.next() instanceof Word && it.hasNext()) {
                sb.append(" ");
            }
        }
        if (!(sb.lastIndexOf(";") == sb.length())) {
            sb.append(".");
        }
        return sb.toString();
    }
}
