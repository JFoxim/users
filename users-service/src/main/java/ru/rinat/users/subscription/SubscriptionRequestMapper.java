package ru.rinat.users.subscription;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rinat.users.userinfo.UserDto;

@Mapper(componentModel = "spring")
public interface SubscriptionRequestMapper {

    default SubscriptionDto toDto(SubscriptionRequest subscriptionRequest, UserDto userCreatorDto, UserDto userSubscriberDto) {
        return toDtoImpl(subscriptionRequest, null, userCreatorDto, userSubscriberDto);
    }

    default SubscriptionDto toDto(SubscriptionRequest subscriptionRequest, Long id, UserDto userCreatorDto, UserDto userSubscriberDto) {
        return toDtoImpl(subscriptionRequest, id, userCreatorDto, userSubscriberDto);
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "creatorUser", source = "userCreatorDto")
    @Mapping(target = "subscriberUser", source = "userSubscriberDto")
    SubscriptionDefaultDto toDtoImpl(SubscriptionRequest subscriptionRequest,
                                     Long id,
                                     UserDto userCreatorDto,
                                     UserDto userSubscriberDto);

    default SubscriptionResponse toResponseDto(SubscriptionDto subscriptionDto) {
        return toResponseImpl((SubscriptionDefaultDto) subscriptionDto);
    }

    SubscriptionResponse toResponseImpl(SubscriptionDefaultDto subscriptionDefaultDto);
}
