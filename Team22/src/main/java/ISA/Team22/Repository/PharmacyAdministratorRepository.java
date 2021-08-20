package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Users.PharmacyAdministrator;

public interface PharmacyAdministratorRepository extends JpaRepository<PharmacyAdministrator, Long> {
	PharmacyAdministrator findByEmail(String email);
}
