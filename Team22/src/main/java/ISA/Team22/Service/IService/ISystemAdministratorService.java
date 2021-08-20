package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.DTO.SupplierDTO;
import ISA.Team22.Domain.DTO.SystemAdminDTO;
import ISA.Team22.Domain.Users.SystemAdministrator;

public interface ISystemAdministratorService {
	 SystemAdministrator findById(Long id);
	 SystemAdministrator findByEmail(String email);
	 List<SystemAdministrator> findAll ();
	 SystemAdministrator save(PersonRequestDTO userRequest);
	 void update(SystemAdminDTO systemAdminDTO);
	 String updatePassword(SystemAdminDTO systemAdminDTO);
	
}
