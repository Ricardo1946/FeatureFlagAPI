package FeatureFlagAPI.ApiFlag.model.mappers;


import FeatureFlagAPI.ApiFlag.model.dto.request.UserEntityRequest;
import FeatureFlagAPI.ApiFlag.model.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityRequestMapper {

    //  from userRequest to userEntity
    UserEntity toUserEntity(UserEntityRequest userEntityRequest);


}
