package ru.rinat.users.rules;

import lombok.Getter;
import ru.rinat.users.validation.RuleConstants;

@Getter
public enum UserValidationRules {
    INSTANCE;
    private final String loginRegex;
    private final String userNameRegex;
    private final String emailRegex;
    private final String phoneRegex;

    private UserValidationRules() {
        this.loginRegex = RuleConstants.LOGIN_REGEX;
        this.userNameRegex = RuleConstants.USER_NAME_REGEX;
        this.emailRegex = RuleConstants.EMAIL_REGEX;
        this.phoneRegex = RuleConstants.PHONE_REGEX;
    }

    public UserValidationRules getInstance() {
        return INSTANCE;
    }
}
