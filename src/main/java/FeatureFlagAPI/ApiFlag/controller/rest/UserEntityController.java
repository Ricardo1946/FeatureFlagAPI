package FeatureFlagAPI.ApiFlag.controller.rest;


import FeatureFlagAPI.ApiFlag.model.dto.UserEntityDto;
import FeatureFlagAPI.ApiFlag.model.dto.request.UserEntityRequest;
import FeatureFlagAPI.ApiFlag.model.services.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Gestión de usuarios")
public class UserEntityController {

    private final UserEntityService userEntityService;

    @PostMapping("/saveUser")
    @Operation(summary = "Crear nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o email duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<UserEntityDto> saveUserEntity(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario a crear",
                    required = true
            )
            UserEntityRequest userEntityRequest) {

        UserEntityDto user = userEntityService.saveUser(userEntityRequest);
        return ResponseEntity.ok().body(user);
    }
}
