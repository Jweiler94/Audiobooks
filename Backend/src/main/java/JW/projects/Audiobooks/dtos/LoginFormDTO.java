package JW.projects.Audiobooks.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginFormDTO {
    private @NotNull @NotBlank String username;
    private @NotNull @NotBlank @Size(
            min = 2,
            max = 30,
            message = "Invalid password. Must be between 2 and 30 characters."
    ) String password;
}
