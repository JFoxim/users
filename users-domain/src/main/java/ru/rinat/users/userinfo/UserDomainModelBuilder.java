package ru.rinat.users.userinfo;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.Gender;
import ru.rinat.users.validation.SimpleValidator;
import ru.rinat.users.validation.ValidationException;
import ru.rinat.users.validation.Validator;
import ru.rinat.users.userinfo.UserDomainModel.Builder;

import java.time.ZonedDateTime;

import static ru.rinat.users.validation.RuleConstants.*;

class UserDomainModelBuilder implements UserDomainModel.Builder {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Gender gender;
    private String email;
    private String phone;
    private ZonedDateTime dateTimeDeleted;

    @Override
    public Builder id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Builder login(String login) {
        this.login = login;
        return this;
    }

    @Override
    public Builder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public Builder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public Builder patronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    @Override
    public Builder gender(Gender gender) {
       this.gender = gender;
        return this;
    }

    @Override
    public Builder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Builder phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public Builder dateTimeDeleted(ZonedDateTime dateTimeDeleted) {
        this.dateTimeDeleted = dateTimeDeleted;
        return this;
    }

    @Override
    public UserDomainModel build() throws DomainModelException {
        try {
            Validator validator = new SimpleValidator();
            return validateAndBuild(validator);
        } catch (ValidationException exception) {
            throw new DomainModelException(exception);
        }
    }

    private UserDomainEntity validateAndBuild(Validator validator) {
        return new UserDomainEntity(
                validator.requireNotNull(id, Error.ID_MESSAGE),
                validator.requireMatchAndNotNull(LOGIN_REGEX, login, Error.LOGIN_MESSAGE),
                validator.requireMatchAndNotNull(USER_NAME_REGEX, firstName, Error.FIRST_NAME_MESSAGE),
                validator.requireMatch(USER_NAME_REGEX, lastName, Error.LAST_NAME_MESSAGE),
                validator.requireMatch(USER_NAME_REGEX, patronymic, Error.LAST_NAME_MESSAGE),
                gender,
                validator.requireMatchAndNotNull(EMAIL_REGEX, email, Error.EMAIL_MESSAGE),
                validator.requireMatch(PHONE_REGEX, phone, Error.PHONE_MESSAGE),
                dateTimeDeleted
        );
    }

    private static final class Error {
        static final String ID_MESSAGE = "User ID cannot be empty";
        static final String LOGIN_MESSAGE = "User login does not match the format";
        static final String FIRST_NAME_MESSAGE = "Username does not match format";
        static final String LAST_NAME_MESSAGE = "User's last name does not match the format";
        static final String EMAIL_MESSAGE = "The email address does not match the format";
        static final String PHONE_MESSAGE = "The phone does not match the format";
    }
}
