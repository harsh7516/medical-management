package medical_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import medical_management.entities.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}