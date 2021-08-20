package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.PharmacyWorkflow.Period;

public interface PeriodRepository extends JpaRepository<Period, Long> {

}
