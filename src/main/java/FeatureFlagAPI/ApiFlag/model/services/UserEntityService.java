package FeatureFlagAPI.ApiFlag.model.services;

import FeatureFlagAPI.ApiFlag.model.dto.UserEntityDto;
import FeatureFlagAPI.ApiFlag.model.dto.request.UserEntityRequest;

public interface UserEntityService {

    UserEntityDto saveUser(UserEntityRequest userEntityRequest);

    UserEntityDto findByEmail(String email);
}
