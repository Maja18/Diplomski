package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.DrugAlternativeDTO;
import ISA.Team22.Domain.DTO.DrugDTO;
import ISA.Team22.Domain.DTO.DrugSearchDTO;
import ISA.Team22.Domain.DTO.DrugSpecificationDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugSpecification;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Repository.DrugRepository;
import ISA.Team22.Service.IService.IDrugService;
import java.util.Collections;

@Service
public class DrugService implements IDrugService {
	
	private final DrugRepository drugRepository;
	private final PatientService patientService;

	@Autowired
	public DrugService(DrugRepository drugRepository, PatientService patientService){
		this.drugRepository = drugRepository;
		this.patientService = patientService;
	}

	@Override
	public Drug save(DrugDTO drugDTO) {
		Drug drug = new Drug();
		drug.setName(drugDTO.getName());
		drug.setCode(drugDTO.getCode());
		drug.setDrugType(drugDTO.getType());
		drug.setDrugForm(drugDTO.getDrugForm());
		drug.setProducer(drugDTO.getSpecification().getManufacturer());
		drug.setIssuance(drugDTO.getIssuance());
		drug.setPoints(drugDTO.getPoints());
        DrugSpecification specification =  new DrugSpecification();
        specification.setContraindications(drugDTO.getSpecification().getContraIndications());
        specification.setCompositions(drugDTO.getSpecification().getComposition());
        specification.setTherapyDuration(drugDTO.getSpecification().getTherapyDuration());
        drug.setDrugSpecification(specification);
        drug.getDrugSpecification().setAlternativeDrugCodes(drugDTO.getAlternativeDrugCodes());
        
        return drugRepository.save(drug);
	}
	
	@Override
	public List<DrugSearchDTO> findDRugForSearch(DrugSearchDTO drugSearchDTO) {
		List<Drug> drugs = findAllDrugs();
		 List<DrugSearchDTO> drugSearchDTOs = new ArrayList<DrugSearchDTO>();
		 for (Drug d : drugs) {
			if(d.getName().equals(drugSearchDTO.getName())){
				DrugSearchDTO drugSearchDTO1 = findDrugSpecification(d);
				drugSearchDTOs.add(drugSearchDTO1);
			}
		}
		return drugSearchDTOs;
	}
	 

	@Override
	public Drug findByName(String name) {
		return drugRepository.findByName(name);
	}

	@Override
	public Drug findByCode(String code) {
		return drugRepository.findByCode(code);
	}

	@Override
	public List<Drug> findAllDrugs() {
		return drugRepository.findAll();
	}
	
	@Override
	public DrugSearchDTO findDrugSpecification(Drug drug) {
		DrugSpecification drugSpecification = drug.getDrugSpecification();
		
		DrugSpecificationDTO  drugSpecificationDTO = new DrugSpecificationDTO();
		drugSpecificationDTO.setContraIndications(drugSpecification.getContraindications());
		drugSpecificationDTO.setComposition(drugSpecification.getCompositions());
		drugSpecificationDTO.setManufacturer(drug.getProducer());
		drugSpecificationDTO.setTherapyDuration(drugSpecification.getTherapyDuration());
		
		DrugSearchDTO drugSearchDTO = new DrugSearchDTO();
		drugSearchDTO.setId(drug.getId());
		drugSearchDTO.setCode(drug.getCode());
		drugSearchDTO.setName(drug.getName());
		drugSearchDTO.setNumberOfGrades(drug.getNumberOfGrades());
		drugSearchDTO.setSpecification(drugSpecificationDTO);
		drugSearchDTO.setDrugForm(drug.getDrugForm());
		drugSearchDTO.setIssuance(drug.getIssuance());
		drugSearchDTO.setType(drug.getDrugType());
		
		return drugSearchDTO;
	}
	
	@Override
	public List<DrugSearchDTO> findDrugs() {
		List<Drug> drugs = findAllDrugs();
        List<DrugSearchDTO> drugsForFront = new ArrayList<>();
        for (Drug drug: drugs) {
        	DrugSearchDTO drugSearchDTO = findDrugSpecification(drug);
        	drugsForFront.add(drugSearchDTO);
        }
		return drugsForFront;
	}

	@Override
	public Drug findById(Long id) {
		return drugRepository.findById(id).get();
	}

	@Override
	public List<DrugSearchDTO> sortDrugs(DrugSearchDTO sortingKey) {
		 List<Drug> drugs = findAllDrugs();
		 List<DrugSearchDTO> drugSearchDTOs = new ArrayList<DrugSearchDTO>();
		 for (Drug d : drugs) {
			 DrugSearchDTO drugSearchDTO1 = findDrugSpecification(d);
			 drugSearchDTOs.add(drugSearchDTO1);
		 }
		
		if(sortingKey.getSortingKey().equals("name")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}else if(sortingKey.getSortingKey().equals("type")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o1.getType().compareTo(o2.getType());
				}
			});
		}else if(sortingKey.getSortingKey().equals("grade")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o1.getNumberOfGrades().compareTo(o2.getNumberOfGrades());
				}
			});
		}
		
		return drugSearchDTOs;
	}
	
	@Override
	public List<DrugSearchDTO> sortDrugsDesc(DrugSearchDTO sortingKey) {
		 List<Drug> drugs = findAllDrugs();
		 List<DrugSearchDTO> drugSearchDTOs = new ArrayList<DrugSearchDTO>();
		 for (Drug d : drugs) {
			 DrugSearchDTO drugSearchDTO1 = findDrugSpecification(d);
			 drugSearchDTOs.add(drugSearchDTO1);
		 }
		
		if(sortingKey.getSortingKey().equals("name")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o2.getName().compareTo(o1.getName());
				}
			});
		}else if(sortingKey.getSortingKey().equals("type")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o2.getType().compareTo(o1.getType());
				}
			});
		}else if(sortingKey.getSortingKey().equals("grade")) {
			Collections.sort(drugSearchDTOs, new Comparator<DrugSearchDTO>() {
				@Override
				public int compare(DrugSearchDTO o1, DrugSearchDTO o2) {
					return o2.getNumberOfGrades().compareTo(o1.getNumberOfGrades());
				}
			});
		
		}
		return drugSearchDTOs;
	}

	@Override
	public List<DrugSearchDTO> getdrugsForPatient(Long id) {
		Patient patient = patientService.findById(id);
		List<Drug> allergies = patient.getDrugs();
		List<Drug> drugs = drugRepository.findAll();
		List<DrugSearchDTO> drugsForPatient = new ArrayList<>();
		
		for(Drug a:allergies)
			for(Drug d:drugs)
				if(!a.getId().equals(d.getId()))
					drugsForPatient.add(findDrugSpecification(d));
		
		if(allergies.isEmpty()) 
			for(Drug d:drugs)
				drugsForPatient.add(findDrugSpecification(d));
		
		
		return drugsForPatient;
	}

	@Override
	public DrugSpecificationDTO getOnlyDrugSpecification(Long id) {
		Drug drug = drugRepository.findById(id).get();
		DrugSpecification drugSpecification = drug.getDrugSpecification();
		
		DrugSpecificationDTO  drugSpecificationDTO = new DrugSpecificationDTO(drugSpecification.getContraindications(),
				drugSpecification.getCompositions(), drugSpecification.getTherapyDuration(), drug.getProducer());
		return drugSpecificationDTO;
	}
	
	@Override
	public List<DrugSearchDTO> findAlternativeDrugs(DrugAlternativeDTO drugAlternativeDTO) {

		Drug drug = drugRepository.findById(drugAlternativeDTO.getDrugId()).get();
		List<Drug> drugsAlternative = drug.getDrugSpecification().getAlternativeDrugCodes();
		Patient patient = patientService.findById(drugAlternativeDTO.getPatientId());
		List<Drug> allergies = patient.getDrugs();
		List<DrugSearchDTO> checkedAlternative = new ArrayList<DrugSearchDTO>();
		
		for(Drug a:allergies)
			for(Drug d:drugsAlternative)
				if(!a.getId().equals(d.getId())) 
					checkedAlternative.add(new DrugSearchDTO(d.getId(), d.getName(), d.getDrugType(), d.getDrugForm(), true));
		
		if(allergies.isEmpty()) 
			for(Drug d:drugsAlternative)
				checkedAlternative.add(new DrugSearchDTO(d.getId(), d.getName(), d.getDrugType(), d.getDrugForm(), true));
		
		
		return checkedAlternative;
	}
	
}
