package ru.rinat.users.news;

import org.mapstruct.Mapper;
import ru.rinat.users.userinfo.UserMapper;

@Mapper(uses = { UserMapper.class })
public interface NewsMapper {

    default NewsDto toDto(NewsDomainModel newsDomainModel) {
        return mapToDtoImpl((NewsDomainEntity) newsDomainModel);
    }

    default NewsDomainEntity toDomainModel(NewsDto newsDto) {
        return mapToDomainEntity((NewsDefaultDto) newsDto);
    }

    NewsDefaultDto mapToDtoImpl(NewsDomainEntity newsDomainEntity);

    NewsDomainEntity mapToDomainEntity(NewsDefaultDto newsDefaultDto);


}
