package mx.edu.utez.sguomm10b.models.User;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {

    private Long id; // Se puede omitir en el create, incluir si actualizas

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre es demasiado largo")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido es demasiado largo")
    private String lastName;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    @NotBlank(message = "El número es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe ser de 10 dígitos")
    private String phoneNumber;

    // Getters y setters

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setLastName(this.lastName);
        entity.setEmail(this.email);
        entity.setPhoneNumber(this.phoneNumber);
        return entity;
    }
}