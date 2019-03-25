package by.epam.javatraining.aksenov.task4.model.exception.logic;

public class SimpleItemWrongTextException extends WrongArgumentException {
    public SimpleItemWrongTextException() {
    }

    public SimpleItemWrongTextException(String message) {
        super(message);
    }
}
