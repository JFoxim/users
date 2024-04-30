package ru.rinat.users.subscription;

import org.mapstruct.Mapper;
import ru.rinat.users.userinfo.UserMapper;

@Mapper(uses = { UserMapper.class })
public interface SubscriptionMapper {

    default SubscriptionDto toDto(SubscriptionDomainModel subscriptionDomainModel) {
        return mapToDtoImpl((SubscriptionDomainEntity)subscriptionDomainModel);
    }

    default SubscriptionDomainModel toDomainModel(SubscriptionDto subscriptionDto) {
        return mapToDomainEntity((SubscriptionDefaultDto) subscriptionDto);
    }

    SubscriptionDefaultDto mapToDtoImpl(SubscriptionDomainEntity subscriptionDomainEntity);

    SubscriptionDomainEntity mapToDomainEntity(SubscriptionDefaultDto subscriptionDefaultDto);
}
