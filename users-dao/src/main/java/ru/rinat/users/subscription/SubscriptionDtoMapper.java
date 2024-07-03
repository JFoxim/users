package ru.rinat.users.subscription;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.rinat.users.userinfo.UserDtoMapper;

@Mapper(componentModel = "spring")
public interface SubscriptionDtoMapper {

    UserDtoMapper USER_DTO_MAPPER = Mappers.getMapper(UserDtoMapper.class);

    default SubscriptionEntity toEntity(SubscriptionDto subscriptionDto) {
        return toEntityImpl((SubscriptionDefaultDto) subscriptionDto);
    }

    SubscriptionEntity toEntityImpl(SubscriptionDefaultDto subscriptionDefaultDto);

    default SubscriptionDto toDto(SubscriptionEntity subscriptionEntity) {
        return toDtoImpl(subscriptionEntity);
    }

    @Mapping(target = "creatorUser", expression = "java(USER_DTO_MAPPER.toDto(subscriptionEntity.getCreatorUser()))")
    @Mapping(target = "subscriberUser", expression = "java(USER_DTO_MAPPER.toDto(subscriptionEntity.getSubscriberUser()))")
    SubscriptionDefaultDto toDtoImpl(SubscriptionEntity subscriptionEntity);

}
