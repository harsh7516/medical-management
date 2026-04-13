package medical_management.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import medical_management.dto.PatientRequestDto;
import medical_management.dto.PatientResponseDto;
import medical_management.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public String register(@RequestBody PatientRequestDto dto){
        return patientService.registerPatient(dto);
    }

    @GetMapping("/{id}")
    public PatientResponseDto getById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping
    public List<PatientResponseDto> getAll(){
        return patientService.getAllPatients();
    }

    @PutMapping("/{id}")
    public PatientResponseDto update(@PathVariable Long id, @RequestBody PatientRequestDto dto) {
        return patientService.updatePatient(id, dto);
}

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return patientService.deletePatient(id);
}
}