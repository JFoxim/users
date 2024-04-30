package ru.rinat.users.validation;


import lombok.extern.slf4j.Slf4j;
import java.util.Collection;

@Slf4j
public class SimpleValidator implements Validator {
    public <T> T requireNotNull(T value, String message) throws ValidationException {
        if (value == null) {
            log.error("Поле не может принимать значение null");
            throw new ValidationException(message);
        }
        return value;
    }

    public String requireNotEmpty(String value, String message) throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(message);
        }
        return value;
    }

    public String requireMatchAndNotNull(String regexPattern, String value, String message) throws ValidationException {
        if (value == null || !value.matches(regexPattern)) {
            log.error("Значение должно соотвествовать формату: {}", regexPattern);
            throw new ValidationException(message);
        }
        return value;
    }
    public String requireMatch(String regexPattern, String value, String message) throws ValidationException {
        if (value == null) return null;
        if (!value.matches(regexPattern)) {
            log.error("Формат значения должен соотвествовать выражению: {}", regexPattern);
            throw new ValidationException(message);
        }
        return value;
    }

    public <T> Collection<T> requireNotEmpty(Collection<T> value, String message) throws ValidationException {
        if (value == null || value.isEmpty()) {
            log.error("Коллекция не может быть пустой");
            throw new ValidationException(message);
        }
        return value;
    }
}