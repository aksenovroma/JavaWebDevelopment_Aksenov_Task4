package by.epam.javatraining.aksenov.task4.model.exception;

public class ParserProjectException extends Exception {
    public ParserProjectException() {
    }

    public ParserProjectException(String message) {
        super(message);
    }

    public ParserProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserProjectException(Throwable cause) {
        super(cause);
    }
}
