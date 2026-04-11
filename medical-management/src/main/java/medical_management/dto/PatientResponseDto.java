package medical_management.dto;

import lombok.Data;

@Data
public class PatientResponseDto {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private String phone;
    private String address;
}