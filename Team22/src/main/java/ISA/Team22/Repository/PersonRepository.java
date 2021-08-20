package ISA.Team22.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Users.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
	 Person findByEmail(String email);
}
