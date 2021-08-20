package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Examination.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

}
