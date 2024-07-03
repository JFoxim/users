package ru.rinat.users.news;


import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;
import ru.rinat.users.validation.SimpleValidator;
import ru.rinat.users.validation.ValidationException;
import ru.rinat.users.validation.Validator;

import java.time.ZonedDateTime;
import java.util.UUID;


class NewsDomainModelBuilder implements NewsDomainModel.Builder {
    private UUID id;
    private UserDomainModel userCreator;
    private String subject;
    private String text;
    private ZonedDateTime createDateTime;

    @Override
    public NewsDomainModel.Builder id(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public NewsDomainModel.Builder userCreator(UserDomainModel userCreator) {
        this.userCreator = userCreator;
        return this;
    }

    @Override
    public NewsDomainModel.Builder subject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public NewsDomainModel.Builder text(String text) {
        this.text = text;
        return this;
    }

    @Override
    public NewsDomainModel.Builder createDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    @Override
    public NewsDomainModel build() throws DomainModelException {
        try {
            Validator validator = new SimpleValidator();
            return validateAndBuild(validator);
        } catch (ValidationException exception) {
            throw new DomainModelException(exception);
        }
    }

    private NewsDomainModel validateAndBuild(Validator validator) {
        return new NewsDomainEntity(
                validator.requireNotNull(id, ErrorMessage.ID),
                validator.requireNotNull(userCreator, ErrorMessage.USER_CREATOR),
                validator.requireNotEmpty(subject, ErrorMessage.SUBJECT),
                validator.requireNotEmpty(text, ErrorMessage.TEXT),
                createDateTime
        );
    }

    private static final class ErrorMessage {
        static final String ID = "News ID cannot be empty";
        static final String USER_CREATOR = "User cannot be empty";
        static final String SUBJECT = "The news topic cannot be empty";
        static final String TEXT = "News text cannot be empty";
    }
}
