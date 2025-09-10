package FeatureFlagAPI.ApiFlag.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserEntityDto {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Length(min = 3, max = 10, message = "El nombre debe tener  como mínimo de 3 a 10 caracteres")
    private String userName;
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato de email no es valido")
    @Length(max = 20, message = "El correo electrónico no debe contener mas de 20 caracteres")
    private String email;

}
