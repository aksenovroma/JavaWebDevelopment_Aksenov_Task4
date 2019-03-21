package by.epam.javatraining.aksenov.task4.model.entity;

import by.epam.javatraining.aksenov.task4.model.logic.Parser;

import java.util.ArrayList;
import java.util.List;

public class Text implements SyntaxItem{
    public static final String REGEX_FOR_PARAGRAPH = "(?m:^$)";

    private String text;
    private List<SyntaxItem> paragraphs = new ArrayList<>();
    private List<String> codeBloks = new ArrayList<>();

    public Text(String text) {
        this.text = text;
        separate();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SyntaxItem> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<SyntaxItem> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<String> getCodeBloks() {
        return codeBloks;
    }

    public void setCodeBloks(List<String> codeBloks) {
        this.codeBloks = codeBloks;
    }

    @Override
    public void separate() {
        String[] text = Parser.parse(this.text, REGEX_FOR_PARAGRAPH);

        for (String paragraph : text) {
            if (Parser.isCodeBlock(paragraph)) {
                codeBloks.add(paragraph);
            } else {
                Paragraph par = new Paragraph(paragraph);
                par.separate();
                paragraphs.add(par);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (SyntaxItem parag : paragraphs) {
            sb.append(parag.toString()).append("\n");
        }
        return sb.toString();
    }
}
