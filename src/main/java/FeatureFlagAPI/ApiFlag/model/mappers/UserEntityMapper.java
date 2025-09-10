package FeatureFlagAPI.ApiFlag.model.mappers;

import FeatureFlagAPI.ApiFlag.model.dto.UserEntityDto;
import FeatureFlagAPI.ApiFlag.model.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    // from userEntity to UserDto
    UserEntityDto userEntityToUserEntityDto(UserEntity userEntity);



}
