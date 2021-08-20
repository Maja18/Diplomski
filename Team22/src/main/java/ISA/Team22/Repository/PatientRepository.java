package ISA.Team22.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Patient findByEmail(String email);
	
	@Query(value = "select e " +
            "from person p left join examination e on p.id = e.patient_id " +
            "where e.patient_id  = :id", nativeQuery = true)
    List<Examination> getAllMyExaminations(@Param("id") Long id);

}
