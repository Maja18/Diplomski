package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.PharmacyWorkflow.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
