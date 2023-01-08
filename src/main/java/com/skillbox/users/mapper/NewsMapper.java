package com.skillbox.users.mapper;

import com.skillbox.users.dto.NewsDto;
import com.skillbox.users.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    NewsDto convert(News news);
}
