package JW.projects.Audiobooks.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterFormDTO extends LoginFormDTO {
    private @Email(
            message = "Invalid email. Try again."
    ) @NotBlank(
            message = "Email is required."
    ) String email;
    private @NotNull(
            message = "Passwords do not match"
    ) String verifyPassword;
}
