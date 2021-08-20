package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.PharmacyAdministrator;

public interface IPharmacyAdministratorService {
	PharmacyAdministrator findById(Long id);
	PharmacyAdministrator findByEmail(String email);
	List<PharmacyAdministrator> findAll ();
	PharmacyAdministrator save(PersonRequestDTO userRequest);

}
