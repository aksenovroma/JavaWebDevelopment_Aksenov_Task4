package by.epam.javatraining.aksenov.task4.model.noname;

public class Word implements SyntaxItem {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
