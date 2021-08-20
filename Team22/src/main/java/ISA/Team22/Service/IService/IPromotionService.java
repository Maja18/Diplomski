package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.DTO.PharmacySubsribedDTO;
import ISA.Team22.Domain.DTO.PromotionDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.Promotion;


public interface IPromotionService {
	List<Promotion> findAllPromotions();
	List<PromotionDTO> findPromotions(PharmacyBasicDTO pharmacyBasicDTO);
	boolean subsribeToPharmacy(Pharmacy pharmacy);
	boolean unsubsribeToPharmacy(Pharmacy pharmacy);
	 List<PharmacySubsribedDTO> findSubscriptionsInPharmacy();

}
