package by.epam.javatraining.aksenov.task4.model.entity;

import java.util.Objects;

public class Punctuation implements SyntaxItem{
    private String punctuation;

    public Punctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punctuation that = (Punctuation) o;
        return Objects.equals(punctuation, that.punctuation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punctuation);
    }

    @Override
    public String toString() {
        return "Punct" + punctuation + "\n";
    }

    @Override
    public void separate() {
        System.out.print(toString());
    }
}
