package by.epam.javatraining.aksenov.task4.model.exception.technical;

import by.epam.javatraining.aksenov.task4.model.exception.ParserProjectException;

public class TechnicalProjectException extends ParserProjectException {
    public TechnicalProjectException() {
    }

    public TechnicalProjectException(String message) {
        super(message);
    }

    public TechnicalProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalProjectException(Throwable cause) {
        super(cause);
    }
}
