package ru.rinat.users.rules;

import lombok.Getter;
import ru.rinat.users.Constants;

@Getter
public enum DateTimeRules {
    INSTANCE;

    private final String timeZone;

    private DateTimeRules() {
        this.timeZone = Constants.MSK_TIME_ZONE;
    }

    public DateTimeRules getInstance() {
        return INSTANCE;
    }

}
