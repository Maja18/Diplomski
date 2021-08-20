package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Pharmacy.PharmacyInventory;
import ISA.Team22.Domain.PharmacyWorkflow.Notification;
import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.DrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.PharmacyDTO;
import ISA.Team22.Domain.DTO.PharmacyDrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.PharmacySubsribedDTO;
import ISA.Team22.Domain.DTO.SortingPharmaciesDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.EPrescription;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Examination.ExaminationStatus;
import ISA.Team22.Domain.Examination.Prescription;
import ISA.Team22.Domain.Examination.PrescriptionStatus;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.PatientRepository;
import ISA.Team22.Repository.PharmacyRepository;
import ISA.Team22.Service.IService.IPharmacyService;

@Service
public class PharmacyService implements IPharmacyService {

	private final PharmacyRepository pharmacyRepository;
	private final PatientRepository patientRepository;
	private final DrugService drugService;
	private final NotificationService notificationService;
	
	@Autowired
    public PharmacyService(PharmacyRepository pharmacyRepository,PatientRepository patientRepository,
    		DrugService drugService,NotificationService notificationService) {
		this.pharmacyRepository = pharmacyRepository;
		this.patientRepository = patientRepository;
		this.drugService = drugService;
		this.notificationService = notificationService;
	}

	@Override
	public List<PharmacyBasicDTO> getAllPharmacyDermatolgist(Long id) {
		List<Pharmacy> pharmacies = pharmacyRepository.getAllPharmacyDermatolgist(id);
		List<PharmacyBasicDTO> pharmaciesDTO = new ArrayList<PharmacyBasicDTO>();
		for(Pharmacy p: pharmacies)
			pharmaciesDTO.add(new PharmacyBasicDTO(p.getId(), p.getName()));
		
		return pharmaciesDTO;
	}

	@Override
	public Pharmacy getById(Long id) {
		 return pharmacyRepository.findById(id).get();
	}
	
	@Override
    public Pharmacy save(PharmacyDTO pharmacy) {
        Pharmacy pharmacy1 = new Pharmacy();
        pharmacy1.setName(pharmacy.getName());
        pharmacy1.setDescription(pharmacy.getDescription());
        AddressDTO addressDTO = pharmacy.getAddressDTO();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        pharmacy1.setAddress(address);
        

        return pharmacyRepository.save(pharmacy1);
    }

	@Override
	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}

	@Override
	public Pharmacy findById(Long pharmacyId) {
		return pharmacyRepository.findById(pharmacyId).get();
	}
	
	@Override
	public boolean canMakeComplaintPharmacy(Long pharmacyId) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(person.getId()).get();
        
        Boolean isAble = false; 
        List<Pharmacy> pharmacies = findAll();
        
        for (Pharmacy pharmacy : pharmacies) {
        	Boolean isAbleExaminations = checkExaminations(pharmacyId, patient, isAble);
        	Boolean isAbleCounselings = checkCounselings(pharmacyId, patient, isAble);
        	Boolean isAbleDrugs = checkDrugs(pharmacyId, patient, isAble);
        	Boolean isAbleEPrescriptions = checkEPrescriptions(pharmacyId, patient, isAble);
        	if(isAbleExaminations || isAbleCounselings || isAbleDrugs) {
        		isAble = true;
        	}
			
		}
     
        return isAble;
	}

	@Override
	public Boolean checkExaminations(Long pharmacyId, Patient patient, Boolean isAble) {
		List<Examination> examinations = patient.getExaminations();
		ExaminationStatus status = ExaminationStatus.held;
		
		for (Examination examination : examinations)
			if(examination.getPharmacy().getId().equals(pharmacyId) && examination.getExaminationStatus().equals(status)) 
				isAble = true;
				
		
		return isAble;
	}
	
	public Boolean checkEPrescriptions(Long pharmacyId, Patient patient, Boolean isAble) {
		List<EPrescription> ePrescriptions = patient.getePrescriptions();
		
		for (EPrescription prescription : ePrescriptions)
			if(prescription.getPharmacyId().equals(pharmacyId))
				isAble = true;
		
		return isAble;
	}
	
	@Override
	public Boolean checkCounselings(Long pharmacyId, Patient patient, Boolean isAble) {
		List<Counseling> counselings = patient.getCounseling();  
		ExaminationStatus status = ExaminationStatus.held;
		
		for (Counseling counseling : counselings)
			if(counseling.getPharmacist().getPharmacy().getId().equals(pharmacyId) && counseling.getCounselingStatus().equals(status)) 
				isAble = true;
				
		return isAble;
	}
	
	@Override
	public Boolean checkDrugs(Long pharmacyId, Patient patient, Boolean isAble) {
		List<Prescription> prescriptions = patient.getPrescription();
		
		for (Prescription prescription : prescriptions)
			if(prescription.getPharmacy().getId().equals(pharmacyId) && prescription.getPrescriptionStatus() == PrescriptionStatus.taken)
					isAble = true;
		
		return isAble;
	}
	
	@Override
	 public List<DrugAvailabilityDTO> getAvailabilityInPharmacies ( String code){
	        List<DrugAvailabilityDTO> pharmacyList = new ArrayList<>();
	        List<Pharmacy> pharmacies = findAll();
	        PharmacyInventory pharmacyInventory = new PharmacyInventory();
	        List<DrugInfo> drugInfos = new ArrayList<DrugInfo>();
	        
	        for (Pharmacy pharmacy : pharmacies) {
	        	pharmacyInventory = pharmacy.getPharmacyInventory();
	        	drugInfos = pharmacyInventory.getDrugInfos();
	        	for (DrugInfo drugInfo : drugInfos) {
					if(drugInfo.getDrug().getCode().equals(code)) {
						pharmacyList.add(new DrugAvailabilityDTO(drugInfo.getDrug().getId(), drugInfo.getDrug().getCode(), pharmacy.getId(), pharmacy.getName(), drugInfo.getPrice()));
					}
				}
			}
	       
	        return pharmacyList;
	    }

	@Override
	public Boolean checkDrugAvailabilityForExamination(DrugAvailabilityDTO drugAvailabilityDTO) {
		Pharmacy pharmacy = pharmacyRepository.findById(drugAvailabilityDTO.getPharmacyId()).get();
		Drug drug = drugService.findById(drugAvailabilityDTO.getDrugId());
		List<DrugInfo> pharmacyDrugs = pharmacy.getPharmacyInventory().getDrugInfos();
		
		for(DrugInfo d:pharmacyDrugs)
			if(d.getDrug().getId().equals(drugAvailabilityDTO.getDrugId()))
				 return true;
		
		Notification notification = new Notification(drug, pharmacy);
		notificationService.saveNotification(notification);
		
		return false;
	}
	
	@Override
	public List<PharmacyDrugAvailabilityDTO> sortPharmacies(SortingPharmaciesDTO sortingPharmaciesDTO) {
		 List<PharmacyDrugAvailabilityDTO> pharmaciesDTO = sortingPharmaciesDTO.getPharmaciesList();
		 
		if(sortingPharmaciesDTO.getSortingKey().equals("pharmacyName")) {
			Collections.sort(pharmaciesDTO, new Comparator<PharmacyDrugAvailabilityDTO>() {
				@Override
				public int compare(PharmacyDrugAvailabilityDTO o1, PharmacyDrugAvailabilityDTO o2) {
					return o1.getPharmacyName().compareTo(o2.getPharmacyName());
				}
			});
		}else if(sortingPharmaciesDTO.getSortingKey().equals("mark")) {
			Collections.sort(pharmaciesDTO, new Comparator<PharmacyDrugAvailabilityDTO>() {
				@Override
				public int compare(PharmacyDrugAvailabilityDTO o1, PharmacyDrugAvailabilityDTO o2) {
					return o1.getMark().compareTo(o2.getMark());
				}
			});
		}else if(sortingPharmaciesDTO.getSortingKey().equals("address")) {
			Collections.sort(pharmaciesDTO, new Comparator<PharmacyDrugAvailabilityDTO>() {
				@Override
				public int compare(PharmacyDrugAvailabilityDTO o1, PharmacyDrugAvailabilityDTO o2) {
					return o1.getAddress().getStreet().compareTo(o2.getAddress().getStreet());
				}
			});
		}
			else if(sortingPharmaciesDTO.getSortingKey().equals("sumPrice")) {
				Collections.sort(pharmaciesDTO, new Comparator<PharmacyDrugAvailabilityDTO>() {
					@Override
					public int compare(PharmacyDrugAvailabilityDTO o1, PharmacyDrugAvailabilityDTO o2) {
						int compared = Double.compare(o1.getSumPrice(), o2.getSumPrice());
						return compared;
					}
				});
		}
		
		return pharmaciesDTO;
	}

	@Override
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findByName(name);
	}

	
	
	
}
