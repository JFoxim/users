package ru.rinat.users.validation;

import java.util.Collection;

public interface Validator {
    <T> T requireNotNull(T value, String fieldName) throws ValidationException;

    String requireNotEmpty(String value, String fieldName) throws ValidationException;

    String requireMatch(String regexPattern, String value, String fieldName) throws ValidationException;

    String requireMatchAndNotNull(String regexPattern, String value, String message) throws ValidationException;

    <T> Collection<T> requireNotEmpty(Collection<T> value, String fieldName) throws ValidationException;
}
