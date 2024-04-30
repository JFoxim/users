package ru.rinat.users.news;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.rinat.users.userinfo.UserDtoMapper;

@Mapper(componentModel = "spring")
public interface NewsDtoMapper {

     UserDtoMapper USER_DTO_MAPPER = Mappers.getMapper(UserDtoMapper.class);

    default NewsEntity toEntity(NewsDto newsDto) {
        return toEntityByImpl((NewsDefaultDto) newsDto);
    }

    NewsEntity toEntityByImpl(NewsDefaultDto newsDefaultDto);

    default NewsDto toDto(NewsEntity newsEntity) {
        return toDtoImpl(newsEntity);
    }

    @Mapping(target = "userCreator", expression = "java(USER_DTO_MAPPER.toDto(newsEntity.getUserCreator()))")
    NewsDefaultDto toDtoImpl(NewsEntity newsEntity);
}
