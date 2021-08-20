package ISA.Team22.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.Pharmacy.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
	
	@Query(value = "select *" +
            "from pharmacy p left join pharmacy_dermatologists pd on p.id = pd.pharmacy_id " +
            "where pd.dermatologist_id = :dermatologist_id", nativeQuery = true)
    List<Pharmacy> getAllPharmacyDermatolgist(@Param("dermatologist_id") Long dermatologist_id);
	Pharmacy findByName(String name);
	
}
