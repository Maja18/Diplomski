package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.PharmacyWorkflow.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
