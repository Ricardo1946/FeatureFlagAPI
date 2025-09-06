package FeatureFlagAPI.ApiFlag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Length(min = 3, max = 10, message = "El nombre debe tener  como mínimo de 3 a 10 caracteres")
    @Column(nullable = false)
    private String userName;
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato de email no es valido")
    @Column(nullable = false, unique = true)
    @Length(max = 20, message = "El correo electrónico no debe contener mas de 20 caracteres")
    private String email;
    @NotBlank(message = "La contraseña no puede ir vacía")
    @Length(min = 5, max = 10, message = "La contraseña debe tener entre 5 y 10 caracteres")
    @Column(nullable = false)
    private String password;

}
