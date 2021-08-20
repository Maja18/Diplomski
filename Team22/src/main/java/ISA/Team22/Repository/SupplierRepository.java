package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ISA.Team22.Domain.Users.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	Supplier findByEmail(String email);
	
	
}
