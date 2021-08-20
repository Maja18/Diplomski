package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Users.SystemAdministrator;

public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
	SystemAdministrator findByEmail(String email);

}
