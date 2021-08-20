package ISA.Team22.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;

public interface BusinessDayDermatologistRepository extends JpaRepository<BusinessDayDermatologist, Long> {
	
	@Query(value = "select *" +
            "from business_day_dermatologist bdd left join period p on bdd.shift_id = p.id" +
            "where bdd.dermatologist_id = :dermatologist_id AND p.start_date = :date", nativeQuery = true)
    List<BusinessDayDermatologist> getAllPharmacyDermatolgist(@Param("dermatologist_id") Long dermatologist_id, @Param("date") LocalDate date);
	}
