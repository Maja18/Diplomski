package ISA.Team22.Service.IService;

import ISA.Team22.Domain.DTO.DermatologistDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.DTO.SupplierDTO;
import ISA.Team22.Domain.Users.Supplier;

public interface ISupplierService {
	Supplier findByEmail(String email);
	Supplier save(PersonRequestDTO userRequest);
	Supplier findById(Long id);
	void update(SupplierDTO supplierDTO);
	String updatePassword(SupplierDTO supplierDTO);
}
