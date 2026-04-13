package medical_management.dto;

import lombok.Data;

@Data
public class DoctorresponceDto {

    private Long id;
    private String name;
    private String email;
    private String specialization;
    private String phone;
    private Integer experience;
}
