package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.DrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.DTO.PharmacyDTO;
import ISA.Team22.Domain.DTO.PharmacyDrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.SortingPharmaciesDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Users.Patient;

public interface IPharmacyService {
	
	List<PharmacyBasicDTO> getAllPharmacyDermatolgist(Long id);
	Pharmacy getById(Long id);
    Pharmacy save(PharmacyDTO pharmacy);
    List<Pharmacy> findAll();
    Pharmacy findById(Long pharmacyId);
    Pharmacy findByName(String name);
    boolean canMakeComplaintPharmacy(Long pharmacyId);
    Boolean checkExaminations(Long pharmacyId, Patient patient, Boolean isAble);
    Boolean checkCounselings(Long pharmacyId, Patient patient, Boolean isAble);
    Boolean checkDrugs(Long pharmacyId, Patient patient, Boolean isAble);
    List<DrugAvailabilityDTO> getAvailabilityInPharmacies ( String code);
    Boolean checkDrugAvailabilityForExamination (DrugAvailabilityDTO drugAvailabilityDTO);
    List<PharmacyDrugAvailabilityDTO> sortPharmacies(SortingPharmaciesDTO sortingPharmaciesDTO);
}
