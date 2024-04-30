package ru.rinat.users.subscription;


import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;
import ru.rinat.users.validation.SimpleValidator;
import ru.rinat.users.validation.ValidationException;
import ru.rinat.users.validation.Validator;

import java.time.ZonedDateTime;


class SubscriptionDomainModelBuilder implements SubscriptionDomainModel.Builder {
    private Long id;
    private UserDomainModel creatorUser;
    private UserDomainModel subscriberUser;
    private ZonedDateTime dateStart;
    private ZonedDateTime dateEnd;

    @Override
    public SubscriptionDomainModel.Builder id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public SubscriptionDomainModel.Builder creatorUser(UserDomainModel creatorUser) {
        this.creatorUser = creatorUser;
        return this;
    }

    @Override
    public SubscriptionDomainModel.Builder subscriberUser(UserDomainModel subscriberUser) {
        this.subscriberUser = subscriberUser;
        return this;
    }

    @Override
    public SubscriptionDomainModel.Builder dateStart(ZonedDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    @Override
    public SubscriptionDomainModel.Builder dateEnd(ZonedDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    @Override
    public SubscriptionDomainModel build() throws DomainModelException {
        try {
            Validator validator = new SimpleValidator();
            return validateAndBuild(validator);
        } catch (ValidationException exception) {
            throw new DomainModelException(exception);
        }
    }

    private SubscriptionDomainModel validateAndBuild(Validator validator) {
        return new SubscriptionDomainEntity(
                validator.requireNotNull(id, ErrorMessage.ID),
                validator.requireNotNull(creatorUser, ErrorMessage.USER_CREATOR),
                validator.requireNotNull(subscriberUser, ErrorMessage.USER_SUBSCRIBER),
                validator.requireNotNull(dateStart, ErrorMessage.DATE_START),
                validator.requireNotNull(dateEnd, ErrorMessage.DATE_END)
        );
    }

    private static final class ErrorMessage {
        static final String ID = "Subscription ID cannot be empty";
        static final String USER_CREATOR = "Subscription UserCreator cannot be empty";
        static final String USER_SUBSCRIBER = "Subscription UserSubscriber cannot be empty";
        static final String DATE_START = "Subscription DateStart cannot be empty";
        static final String DATE_END = "Subscription DateEnd cannot be empty";
    }
}
