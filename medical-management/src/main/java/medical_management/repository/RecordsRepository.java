package medical_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import medical_management.entities.RecordEntity;

public interface RecordsRepository extends JpaRepository<RecordEntity, Long>{

}
