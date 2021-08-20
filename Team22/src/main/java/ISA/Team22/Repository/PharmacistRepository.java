package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.Users.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	 
	 Pharmacist findByEmail(String email);
	 
	 @Query("select p from Person p where p.id = :id")
	 public Pharmacist findOneById(@Param("id")Long id);

}
