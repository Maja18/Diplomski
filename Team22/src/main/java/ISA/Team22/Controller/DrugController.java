package ISA.Team22.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.DrugAlternativeDTO;
import ISA.Team22.Domain.DTO.DrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.DrugDTO;
import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.DrugSearchDTO;
import ISA.Team22.Domain.DTO.DrugSpecificationDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Service.DrugService;

@RestController
@RequestMapping("/api/drug")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DrugController {

	private final  DrugService drugService;
	
	@Autowired
	public DrugController(DrugService drugService) {
		this.drugService = drugService;
	}
	
	@PostMapping("/addDrug")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    ResponseEntity<Drug> addDrug(@RequestBody DrugDTO drugDTO)
    {
        Drug existingDrug = drugService.findByCode(drugDTO.getCode());
        if(existingDrug != null)
        {
            throw new IllegalArgumentException("Drug code already exists!");
        }
        if(drugDTO.getSpecification().getManufacturer().isEmpty() || drugDTO.getSpecification().getComposition().isEmpty() || 
        		drugDTO.getSpecification().getContraIndications().isEmpty() || drugDTO.getSpecification().getTherapyDuration() <= 0) {
        	throw new IllegalArgumentException("Drug must have specification, please fill all specification fields!");
        }
        
        Drug drug = drugService.save(drugDTO);
        return (ResponseEntity<Drug>) (drug == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(drug));
    }
	
	@GetMapping("getdrugs")
	//@PreAuthorize("hasAnyRole('PATIENT', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST','UNAUTHORISED')")
	//@PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    ResponseEntity<List<DrugSearchDTO>> getAllDrugs()
    {
        List<DrugSearchDTO> drugsForFront = drugService.findDrugs();
        
        return drugsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(drugsForFront);
    }
	
	 
	 @PostMapping("/searchDrug")
	 //@PreAuthorize("hasAnyRole('PATIENT', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST','UNAUTHORISED')")
		public ResponseEntity<List<DrugSearchDTO>> searchDrug(@RequestBody DrugSearchDTO drugSearchDTO) {
		 List<DrugSearchDTO> drugSearchDTOs = drugService.findDRugForSearch(drugSearchDTO);
	        
	        return drugSearchDTOs == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(drugSearchDTOs);
		  
		}

	 @PostMapping("/getDrugSpecification")
	 //@PreAuthorize("hasAnyRole('PATIENT', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST','UNAUTHORISED')")
		public ResponseEntity<DrugSearchDTO> findDrugSpecification(@RequestBody DrugSearchDTO drugSearchDTO) {
		    Drug drug = drugService.findById(drugSearchDTO.getId());
		  
		    DrugSearchDTO drugSearchDTO1 = drugService.findDrugSpecification(drug);
	        
	        return drugSearchDTO1 == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(drugSearchDTO1);
		  
		}
	 
	 @PostMapping("/sortResult")
	 //@PreAuthorize("hasAnyRole('PATIENT', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST','UNAUTHORISED')")
		public ResponseEntity<List<DrugSearchDTO>> sortDrugs(@RequestBody DrugSearchDTO sortingKey) {
			try {
				List<DrugSearchDTO> searchResult = drugService.sortDrugs(sortingKey);
				
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	 
	 @PostMapping("/sortResultDescending")
		public ResponseEntity<List<DrugSearchDTO>> sortDrugsDesc(@RequestBody DrugSearchDTO sortingKey) {
			try {
				List<DrugSearchDTO> searchResult = drugService.sortDrugsDesc(sortingKey);
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

	@PostMapping("/getdrugsForPatient")
	@PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<DrugSearchDTO>> getdrugsForPatient(@RequestBody DrugSearchDTO id)
	{
		List<DrugSearchDTO> searchResult = drugService.getdrugsForPatient(id.getId());
	    return searchResult == null ?  new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(searchResult);
	}
	
	@GetMapping("/getOnlyDrugSpecification/{drugID}")
	@PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
	public ResponseEntity<DrugSpecificationDTO> getOnlyDrugSpecification(@PathVariable Integer drugID)
	{
		Long id = (long) drugID;
		DrugSpecificationDTO searchResult = drugService.getOnlyDrugSpecification(id);
	    return searchResult == null ?  new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(searchResult);
	 }
	 
	@PostMapping("/getAlternativeDrugs")
	@PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<DrugSearchDTO>> findAlternativeDrugs(@RequestBody DrugAlternativeDTO drugAlternativeDTO)
	{
		try {
			System.out.println(drugAlternativeDTO.getPatientId());
			System.out.println(drugAlternativeDTO.getDrugId());
			List<DrugSearchDTO> searchResult = drugService.findAlternativeDrugs(drugAlternativeDTO);
			return ResponseEntity.ok(searchResult);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
