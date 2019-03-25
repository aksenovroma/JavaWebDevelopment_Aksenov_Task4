package by.epam.javatraining.aksenov.task4.model.exception.logic;

public class CompositeItemWrongTextException extends WrongArgumentException {
    public CompositeItemWrongTextException() {
    }

    public CompositeItemWrongTextException(String message) {
        super(message);
    }
}
