package medical_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import medical_management.dto.DoctorRequestDto;
import medical_management.dto.DoctorresponceDto;
import medical_management.entities.DoctorsEntity;
import medical_management.repository.DoctorsRepository;

@Service
public class DoctorService {

    private final DoctorsRepository doctorRepository;

    public DoctorService(DoctorsRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public String registerDoctor(DoctorRequestDto dto) {
        Optional<DoctorsEntity> existingDoctor = doctorRepository.findByEmail(dto.getEmail());

        if (existingDoctor.isPresent()) {
            throw new RuntimeException("Doctor already exists with this email");
        }

        DoctorsEntity doctorEntity = new DoctorsEntity();
        doctorEntity.setName(dto.getName());
        doctorEntity.setEmail(dto.getEmail());
        doctorEntity.setSpecialization(dto.getSpecialization());
        doctorEntity.setPhone(dto.getPhone());
        doctorEntity.setExperience(dto.getExperience());

        doctorRepository.save(doctorEntity);

        return "Doctor registered successfully";
    }

    public DoctorresponceDto getDoctorById(Long id) {

        DoctorsEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorresponceDto response = new DoctorresponceDto();

        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setEmail(doctor.getEmail());
        response.setSpecialization(doctor.getSpecialization());
        response.setPhone(doctor.getPhone());
        response.setExperience(doctor.getExperience());

        return response;
    }

    public List<DoctorresponceDto> getAllDoctors() {

        List<DoctorsEntity> doctors = doctorRepository.findAll();

        List<DoctorresponceDto> responseList = new ArrayList<>();

        for (DoctorsEntity doctor : doctors) {
            DoctorresponceDto response = new DoctorresponceDto();

            response.setId(doctor.getId());
            response.setName(doctor.getName());
            response.setEmail(doctor.getEmail());
            response.setSpecialization(doctor.getSpecialization());
            response.setPhone(doctor.getPhone());
            response.setExperience(doctor.getExperience());

            responseList.add(response);
        }

        return responseList;
    }

    public DoctorresponceDto updateDoctor(Long id, DoctorRequestDto dto) {

        DoctorsEntity doctorEntity = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorEntity.setName(dto.getName());
        doctorEntity.setEmail(dto.getEmail());
        doctorEntity.setSpecialization(dto.getSpecialization());
        doctorEntity.setPhone(dto.getPhone());
        doctorEntity.setExperience(dto.getExperience());

        doctorRepository.save(doctorEntity);

        DoctorresponceDto response = new DoctorresponceDto();
        response.setId(doctorEntity.getId());
        response.setName(doctorEntity.getName());
        response.setEmail(doctorEntity.getEmail());
        response.setSpecialization(doctorEntity.getSpecialization());
        response.setPhone(doctorEntity.getPhone());
        response.setExperience(doctorEntity.getExperience());

        return response;
    }

    public String deleteDoctor(Long id) {

        DoctorsEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorRepository.delete(doctor);

        return "Doctor deleted successfully";
    }
}