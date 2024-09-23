package ru.rinat.users.errors;


import java.util.function.Supplier;

public class UsersServiceException extends RuntimeException implements Supplier<UsersServiceException> {
    private String code;
    private String message;

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

    protected UsersServiceException() {
        super();
    }

    public static Builder builder() {
        return new UsersServiceException().new Builder();
    }

    @Override
    public UsersServiceException get() {
        return this;
    }

    public class Builder {
        public UsersServiceException build() {
            return UsersServiceException.this;
        }
        private Builder() {super();}

        public Builder code(String code) {
           UsersServiceException.this.code = code;
           return this;
        }

        public Builder message(String message) {
            UsersServiceException.this.message = message;
            return this;
        }
    }
}
