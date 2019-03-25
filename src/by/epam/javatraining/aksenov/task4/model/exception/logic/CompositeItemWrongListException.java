package by.epam.javatraining.aksenov.task4.model.exception.logic;

public class CompositeItemWrongListException extends WrongArgumentException {
    public CompositeItemWrongListException() {
    }

    public CompositeItemWrongListException(String message) {
        super(message);
    }
}
