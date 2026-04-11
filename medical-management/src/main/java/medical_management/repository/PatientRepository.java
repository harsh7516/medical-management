package medical_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import medical_management.entities.PatientEntity;


public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByEmail(String name);

}
