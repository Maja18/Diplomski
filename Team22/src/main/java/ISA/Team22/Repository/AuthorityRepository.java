package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Users.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findByName(String name);
}
