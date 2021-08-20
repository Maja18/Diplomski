package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.Team22.Domain.Users.Dermatologist;


public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	
	 Dermatologist findByEmail(String email);
	 
	 @Query("select p from Person p where p.id = :id")
	 public Dermatologist findOneById(@Param("id")Long id);


}
