package ru.rinat.users.validation;

public final class RuleConstants {
    public static final String LOGIN_REGEX = "^(?=.*[A-Za-zА-Яа-я0-9]$)[A-Za-zА-Яа-я][A-Za-zА-Яа-я\\d.-]{0,19}$";
    public static final String USER_NAME_REGEX = "^([А-Яа-я]\\s{0,1}){0,30}$";
    public static final String EMAIL_REGEX = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String PHONE_REGEX = "^\\d{10}$";

}
