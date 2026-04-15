package medical_management.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import medical_management.dto.AppointmentRequestDto;
import medical_management.dto.AppointmentResponseDto;
import medical_management.entities.AppointmentEntity;
import medical_management.entities.DoctorsEntity;
import medical_management.entities.PatientEntity;
import medical_management.repository.AppointmentRepository;
import medical_management.repository.DoctorsRepository;
import medical_management.repository.PatientRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorsRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorsRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // 🔥 BOOK APPOINTMENT
    public String bookAppointment(AppointmentRequestDto dto) {

        PatientEntity patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        DoctorsEntity doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDateTime(LocalDateTime.parse(dto.getAppointmentDateTime()));
        appointment.setStatus(dto.getStatus());

        // ✅ IMPORTANT FIX (symptoms)
        appointment.setSymptoms(dto.getSymptoms());

        appointmentRepository.save(appointment);

        return "Appointment booked successfully";
    }

    // 🔍 GET BY ID
    public AppointmentResponseDto getAppointmentById(Long id) {

        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return mapToDto(appointment);
    }

    // 📋 GET ALL
    public List<AppointmentResponseDto> getAllAppointments() {

        List<AppointmentEntity> appointments = appointmentRepository.findAll();
        List<AppointmentResponseDto> responseList = new ArrayList<>();

        for (AppointmentEntity appointment : appointments) {
            responseList.add(mapToDto(appointment));
        }

        return responseList;
    }

    // ✏️ UPDATE
    public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto dto) {

        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        PatientEntity patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        DoctorsEntity doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDateTime(LocalDateTime.parse(dto.getAppointmentDateTime()));
        appointment.setStatus(dto.getStatus());

        // ✅ IMPORTANT FIX
        appointment.setSymptoms(dto.getSymptoms());

        appointmentRepository.save(appointment);

        return mapToDto(appointment);
    }

    // ❌ DELETE
    public String deleteAppointment(Long id) {

        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointmentRepository.delete(appointment);

        return "Appointment deleted successfully";
    }

    // 🔄 ENTITY → DTO
    private AppointmentResponseDto mapToDto(AppointmentEntity appointment) {

        AppointmentResponseDto response = new AppointmentResponseDto();

        response.setId(appointment.getId());
        response.setPatientId(appointment.getPatient().getId());
        response.setPatientName(appointment.getPatient().getName());
        response.setDoctorId(appointment.getDoctor().getId());
        response.setDoctorName(appointment.getDoctor().getName());
        response.setAppointmentDateTime(appointment.getAppointmentDateTime().toString());
        response.setStatus(appointment.getStatus());

        return response;
    }
}