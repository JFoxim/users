package ru.rinat.users;

public class DomainModelException extends RuntimeException {

    public DomainModelException(String message) {
        super(message);
    }

    public DomainModelException(Throwable cause) {
        super(cause);
    }

    public DomainModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
