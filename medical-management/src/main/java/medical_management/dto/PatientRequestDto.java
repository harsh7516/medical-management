package medical_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientRequestDto {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer age;

    @NotBlank
    private String gender;
    private String phone;
    private String address;
}
