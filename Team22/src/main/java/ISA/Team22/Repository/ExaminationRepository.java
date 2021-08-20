package ISA.Team22.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	@Query(value = "select *" +
            "from examination e left join person p on e.patient_id = p.id" +
            "where p.id = :patient_id ", nativeQuery = true)
    List<Examination> getAllPatientExaminations(@Param("patient_id") Long patient_id);
	
	@Query(value = "select e" +
            "from examination e" +
            "where e.id = :examination_id ", nativeQuery = true)
    Examination findSpecifiedexamination(@Param("examination_id") Long examination_id);
	
}
