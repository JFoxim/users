package ru.rinat.users.news;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsDtoMapper {

    default NewsEntity toEntity(NewsDto newsDto) {
        return toEntityByImpl((NewsDefaultDto) newsDto);
    }

    NewsEntity toEntityByImpl(NewsDefaultDto newsDefaultDto);

    default NewsDto toDto(NewsEntity newsEntity) {
        return toDtoImpl(newsEntity);
    }

    @Mapping(target = "userCreator.isDeleted", ignore = true)
    NewsDefaultDto toDtoImpl(NewsEntity newsEntity);
}
