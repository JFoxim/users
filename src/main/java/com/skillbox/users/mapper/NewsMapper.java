package com.skillbox.users.mapper;

import com.skillbox.users.dto.NewsDto;
import com.skillbox.users.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsDto toNewsDto(News news);
    News toNewsEntity(NewsDto newsDto);
}
