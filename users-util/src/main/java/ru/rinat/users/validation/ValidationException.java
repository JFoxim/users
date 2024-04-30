package ru.rinat.users.validation;


public class ValidationException extends RuntimeException {
      private final String message;
    private final String code;

    public ValidationException(String message) {
        this.message = message;
        this.code = "VALIDATION_EXCEPTION";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
