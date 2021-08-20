package ISA.Team22.Service.IService;

import ISA.Team22.Domain.DTO.LoaltyProgramDTO;
import ISA.Team22.Domain.PharmacyWorkflow.LoyaltyProgram;


public interface ILoyaltyProgramService {
	LoyaltyProgram save(LoaltyProgramDTO loaltyProgramDTO);

}
