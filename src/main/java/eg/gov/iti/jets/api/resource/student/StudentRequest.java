package eg.gov.iti.jets.api.resource.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotBlank(message = "Username should not be empty or null")
    @Size(min = 3, max = 25 , message = "User name length should be between 3 and 25 characters")
    private String username;
    @NotBlank(message = "Email should not be empty or null")
    @Email(message = "Email should be in this format example@example.example")
    private String email;
    @NotNull(message = "TrackId should not be null")
    private Integer trackId;
}
