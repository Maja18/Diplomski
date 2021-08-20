package ISA.Team22.Service.IService;


import java.util.List;

import ISA.Team22.Domain.DTO.DrugAlternativeDTO;
import ISA.Team22.Domain.DTO.DrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.DrugDTO;
import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.DrugSearchDTO;
import ISA.Team22.Domain.DTO.DrugSpecificationDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;


public interface IDrugService {
	
	Drug save(DrugDTO drugDTO);
	Drug findByName(String name);
	Drug findByCode(String code);
    List<Drug> findAllDrugs();
    Drug findById(Long id);
    List<DrugSearchDTO> sortDrugs(DrugSearchDTO sortingKey);
    DrugSearchDTO findDrugSpecification(Drug drug);
    List<DrugSearchDTO> getdrugsForPatient(Long id);
    DrugSpecificationDTO getOnlyDrugSpecification(Long id);
    List<DrugSearchDTO> findAlternativeDrugs (DrugAlternativeDTO drugAlternativeDTO);
    List<DrugSearchDTO> findDrugs();
    List<DrugSearchDTO> findDRugForSearch(DrugSearchDTO drugSearchDTO);
    List<DrugSearchDTO> sortDrugsDesc(DrugSearchDTO sortingKey);
}
