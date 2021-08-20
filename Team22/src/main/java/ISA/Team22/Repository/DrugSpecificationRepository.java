package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.DrugManipulation.DrugSpecification;

public interface DrugSpecificationRepository extends JpaRepository<DrugSpecification, Long> {

}
