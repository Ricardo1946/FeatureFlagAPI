package FeatureFlagAPI.ApiFlag.model.services.Impl;


import FeatureFlagAPI.ApiFlag.common.exception.ResourceNotFoundException;
import FeatureFlagAPI.ApiFlag.model.dto.UserEntityDto;
import FeatureFlagAPI.ApiFlag.model.dto.request.UserEntityRequest;
import FeatureFlagAPI.ApiFlag.model.mappers.UserEntityMapper;
import FeatureFlagAPI.ApiFlag.model.mappers.UserEntityRequestMapper;
import FeatureFlagAPI.ApiFlag.model.repository.UserEntityRepository;
import FeatureFlagAPI.ApiFlag.model.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityRequestMapper userEntityRequestMapper;
    private final UserEntityMapper userEntityMapper;


    @Override
    public UserEntityDto saveUser(UserEntityRequest userEntityRequest) {
        log.info("Saving user entity request : {}", userEntityRequest.getUserName());

        // validate if User exists
        userEntityRepository.findByEmail(userEntityRequest.getEmail()).ifPresent(userEntity -> {
            log.warn("Creating new user : {}", userEntityRequest.getEmail());
            throw new IllegalStateException("User already exists : " + userEntityRequest.getEmail());
        });

        try {
            var userToSave = userEntityRepository.save(userEntityRequestMapper.toUserEntity(userEntityRequest));
            log.info("User saved : {}", userToSave.getUserName());
            return userEntityMapper.userEntityToUserEntityDto(userToSave);

        } catch (Exception e) {
            log.error("Error while saving user entity : {}", userEntityRequest.getEmail(), e);
            throw new IllegalStateException("Error while saving user entity : " + userEntityRequest.getEmail(), e);
        }

    }

    @Override
    public UserEntityDto findByEmail(String email) {
        log.info("Buscando usuario por email: {}", email);

        return userEntityRepository.findByEmail(email)
                .map(userEntityMapper::userEntityToUserEntityDto)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado con email: {}", email);
                    return new ResourceNotFoundException(
                            "Usuario con email " + email + " no encontrado"
                    );
                });
    }



}
