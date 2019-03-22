package by.epam.javatraining.aksenov.task4.model.noname;

public class Punctuation implements SyntaxItem {
    private String punctuation;

    public Punctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public String toString() {
        return punctuation;
    }
}
