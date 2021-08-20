package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.LoaltyProgramDTO;
import ISA.Team22.Domain.PharmacyWorkflow.LoyaltyProgram;
import ISA.Team22.Repository.LoyaltyProgramRepository;
import ISA.Team22.Service.IService.ILoyaltyProgramService;

@Service
public class LoyaltyProgramService implements ILoyaltyProgramService {

	@Autowired
	private  LoyaltyProgramRepository loyaltyProgramRepository;

	@Override
	public LoyaltyProgram save(LoaltyProgramDTO loaltyProgramDTO) {
		LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.setConsultingPoints(loaltyProgramDTO.getConsultingPoints());
		loyaltyProgram.setExaminationPoints(loaltyProgramDTO.getExaminationPoints());
		loyaltyProgram.setRegularDiscount(loaltyProgramDTO.getRegularDiscount());
		loyaltyProgram.setSilverDiscount(loaltyProgramDTO.getSilverDiscount());
		loyaltyProgram.setGoldDiscount(loaltyProgramDTO.getGoldDiscount());
		loyaltyProgram.setMinimumSilverPoints(loaltyProgramDTO.getMinimumSilverPoints());
		loyaltyProgram.setMinimumGoldPoints(loaltyProgramDTO.getMinimumGoldPoints());
		
		return loyaltyProgramRepository.save(loyaltyProgram);
	}

}
