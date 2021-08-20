package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Examination.EPrescription;

public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long>{
	EPrescription findByCode(String code);
}
