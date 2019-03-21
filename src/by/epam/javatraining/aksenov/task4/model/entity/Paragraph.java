package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.logic.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Paragraph implements SyntaxItem{
    public static final String REGEX_FOR_SENTENCES = "(?<=[a-z])\\.\\s+";

    private String paragraph;
    private List<SyntaxItem> sentences = new ArrayList<>();

    public Paragraph() {
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<SyntaxItem> getSentences() {
        return sentences;
    }

    public void setSentences(List<SyntaxItem> sentences) {
        this.sentences = sentences;
    }

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public void separate() {
        String[] sentences = Parser.parse(paragraph, REGEX_FOR_SENTENCES);

        for (String sentence : sentences) {
            Sentence sent = new Sentence(sentence);
            sent.separate();
            this.sentences.add(sent);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (SyntaxItem sent : sentences) {
            sb.append(sent.toString()).append(" ");
        }
        return sb.toString();
    }
}
