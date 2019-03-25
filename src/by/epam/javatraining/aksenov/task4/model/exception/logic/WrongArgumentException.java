package by.epam.javatraining.aksenov.task4.model.exception.logic;

public class WrongArgumentException extends LogicProjectException {
    public WrongArgumentException() {
    }

    public WrongArgumentException(String message) {
        super(message);
    }
}
