package medical_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import medical_management.dto.PatientRequestDto;
import medical_management.dto.PatientResponseDto;
import medical_management.entities.PatientEntity;
import medical_management.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public String registerPatient(PatientRequestDto dto){
        Optional<PatientEntity> existingPatient = patientRepository.findByEmail(dto.getEmail());
    
    
        if (existingPatient.isPresent()){
            throw new RuntimeException("Patient already exist with this email");
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(dto.getName());
        patientEntity.setEmail(dto.getEmail());
        patientEntity.setAge(dto.getAge());
        patientEntity.setGender(dto.getGender());
        patientEntity.setPhone(dto.getPhone());
        patientEntity.setAddress(dto.getAddress());

        patientRepository.save(patientEntity);
        return "patient register successfully";


    }

    public PatientResponseDto getPatientById(Long id) {

    PatientEntity patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

    PatientResponseDto response = new PatientResponseDto();

    response.setId(patient.getId());
    response.setName(patient.getName());
    response.setEmail(patient.getEmail());
    response.setAge(patient.getAge());
    response.setGender(patient.getGender());
    response.setPhone(patient.getPhone());
    response.setAddress(patient.getAddress());

    return response;
}

public List<PatientResponseDto> getAllPatients() {

    List<PatientEntity> patients = patientRepository.findAll();

    List<PatientResponseDto> responseList = new ArrayList<>();

    for (PatientEntity patient : patients) {
        PatientResponseDto response = new PatientResponseDto();

        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setEmail(patient.getEmail());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setPhone(patient.getPhone());
        response.setAddress(patient.getAddress());

        responseList.add(response);
    }

    return responseList;
}

}
