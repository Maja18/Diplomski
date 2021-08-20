package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.DrugManipulation.DrugReservation;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long>{

}
