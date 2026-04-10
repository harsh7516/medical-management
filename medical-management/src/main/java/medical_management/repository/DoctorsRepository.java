package medical_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import medical_management.entities.DoctorsEntity;

public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Long> {

}
