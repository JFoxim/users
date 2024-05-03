package ru.rinat.users.errors;

public class UsersServiceException extends RuntimeException {
    private String code;

    public UsersServiceException(String message) {
        super(message);
    }

    public UsersServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public UsersServiceException(Throwable cause) {
        super(cause);
    }

    public UsersServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

}
