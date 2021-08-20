package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.Users.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {

}
