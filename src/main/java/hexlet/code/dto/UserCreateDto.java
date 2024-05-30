package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    @NotBlank
    @Email
    private String email;
    private String firstName;
    private String lastName;
    @NotBlank
    @Size(min = 3)
    private String password;
}
