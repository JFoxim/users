package ru.rinat.users.contact;


import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;
import ru.rinat.users.validation.SimpleValidator;
import ru.rinat.users.validation.ValidationException;
import ru.rinat.users.validation.Validator;


class UserContactDomainModelBuilder implements UserContactDomainModel.Builder {
    private Long id;
    private UserDomainModel user;
    private String value;
    private String type;

    @Override
    public UserContactDomainModel.Builder id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public UserContactDomainModel.Builder user(UserDomainModel user) {
        this.user = user;
        return this;
    }

    @Override
    public UserContactDomainModel.Builder value(String value) {
        this.value = value;
        return this;
    }

    @Override
    public UserContactDomainModel.Builder type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public UserContactDomainModel build() throws DomainModelException {
        try {
            Validator validator = new SimpleValidator();
            return validateAndBuild(validator);
        } catch (ValidationException exception) {
            throw new DomainModelException(exception);
        }
    }

    private UserContactDomainModel validateAndBuild(Validator validator) {
        return new UserContactDomainEntity(
                validator.requireNotNull(id, ErrorMessage.ID),
                validator.requireNotNull(user, ErrorMessage.USER),
                validator.requireNotEmpty(value, ErrorMessage.VALUE),
                validator.requireNotEmpty(type, ErrorMessage.TYPE)
        );
    }

    private static final class ErrorMessage {
        static final String ID = "UserContactInfo.id cannot be empty";
        static final String USER = "UserContactInfo.user cannot be empty";
        static final String VALUE = "UserContactInfo.value field cannot be empty";
        static final String TYPE = "UserContactInfo.type field cannot be empty";
    }
}
