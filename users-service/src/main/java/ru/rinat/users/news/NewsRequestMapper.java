package ru.rinat.users.news;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rinat.users.userinfo.UserDto;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface NewsRequestMapper {

    default NewsDto toDto(NewsRequest newsRequest, UserDto userDto) {
        return toDtoImpl(newsRequest, null, userDto);
    }

    default NewsDto toDto(NewsRequest newsRequest, UUID id, UserDto userDto) {
        return toDtoImpl(newsRequest, id, userDto);
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userCreator", source = "userDto")
    NewsDefaultDto toDtoImpl(NewsRequest newsRequest, UUID id, UserDto userDto);

    default NewsResponse toResponseDto(NewsDto newsDto) {
        return toResponseImpl((NewsDefaultDto)newsDto);
    }

    NewsResponse toResponseImpl(NewsDefaultDto newsDefaultDto);
}
