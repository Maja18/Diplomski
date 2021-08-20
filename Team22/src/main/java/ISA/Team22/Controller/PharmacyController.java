package ISA.Team22.Controller;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import ISA.Team22.Domain.DTO.DrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.DrugDTO;
import ISA.Team22.Domain.DTO.DrugSearchDTO;
import ISA.Team22.Domain.DTO.EPrescriptionFrontDTO;
import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.DTO.PharmacyDTO;
import ISA.Team22.Domain.DTO.PharmacyDrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.QRCodeDTO;
import ISA.Team22.Domain.DTO.SortingPharmaciesDTO;
import ISA.Team22.Domain.DTO.UserInfoComplaintDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.Examination.EPrescription;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Pharmacy.PharmacyInventory;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Service.DermatologistService;
import ISA.Team22.Service.EPrescriptionService;
import ISA.Team22.Service.EmailService;
import ISA.Team22.Service.PatientService;
import ISA.Team22.Service.PharmacyService;

@RestController
@RequestMapping(value = "/api/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {

	private final PharmacyService pharmacyService;
	private final DermatologistService dermatologistService;
	private final PatientService patientService;
	private final EPrescriptionService ePrescriptionService;
	private final EmailService emailServices;

	@Autowired
	public PharmacyController(PharmacyService pharmacyService, DermatologistService dermatologistService,PatientService patientService,
			EPrescriptionService ePrescriptionService,EmailService emailServices) {
		this.pharmacyService = pharmacyService;
		this.dermatologistService = dermatologistService;
		this.patientService = patientService;
		this.ePrescriptionService = ePrescriptionService;
		this.emailServices = emailServices;
	}

	@GetMapping("/pharmacystaff/dermatologist")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<PharmacyBasicDTO>> getAllPharmacyDermatolgist() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Dermatologist dermatologist = dermatologistService.getById(person.getId());
		List<PharmacyBasicDTO> pharmacies = pharmacyService.getAllPharmacyDermatolgist(dermatologist.getId());
		
		for(PharmacyBasicDTO p:pharmacies) {
			System.out.println(p.getName());
		}
		
		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}

	@PostMapping("/registerPharmacy")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyDTO pharmacyDTO) {

        Pharmacy pharmacy = pharmacyService.save(pharmacyDTO);
        return new ResponseEntity<>("Pharmacy is successfully registred!", HttpStatus.CREATED);
    }
	
	@GetMapping("/pharmacies")
	@PreAuthorize("hasRole('PATIENT')")  //mozda treba za jos koju rolu
	public ResponseEntity<List<PharmacyBasicDTO>> getAllPharmacies() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		List<PharmacyBasicDTO> pharmaciesDTOs = new ArrayList<PharmacyBasicDTO>();
		
		for(Pharmacy p:pharmacies) {
			PharmacyBasicDTO pharmacyBasicDTO = new PharmacyBasicDTO(p.getId(), p.getName());
			pharmaciesDTOs.add(pharmacyBasicDTO);
		}
		
		return ResponseEntity.ok(pharmaciesDTOs);
	}
	
	
	@PostMapping("/checkDrugAvailability")
	//@PreAuthorize("hasAnyRole('PATIENT', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST','UNAUTHORISED')")
    public ResponseEntity<List<DrugAvailabilityDTO>> getAvailability ( @RequestBody DrugAvailabilityDTO drugAvailabilityDTO){
		 
        List<DrugAvailabilityDTO> drugAvailabilityDTOs = pharmacyService.getAvailabilityInPharmacies(drugAvailabilityDTO.getDrugCode());

        return drugAvailabilityDTOs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(drugAvailabilityDTOs);
    }
    
    @PostMapping("/isDrugAvailable")
    @PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
    public ResponseEntity<Boolean> checkDrugAvailabilityForExamination ( @RequestBody DrugAvailabilityDTO drugAvailabilityDTO){
		 
    	Boolean isAvailable = pharmacyService.checkDrugAvailabilityForExamination(drugAvailabilityDTO);

        return isAvailable == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(isAvailable);
    }
    
    @PostMapping("/sortResult")
    @PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<List<PharmacyDrugAvailabilityDTO>> sortResults(@RequestBody SortingPharmaciesDTO sortingPharmaciesDTO) {
    	try {
				List<PharmacyDrugAvailabilityDTO> searchResult = pharmacyService.sortPharmacies(sortingPharmaciesDTO);
				
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
    
    @PostMapping("/buyDrugsHere")
    @PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<String> buyDrugsHere(@RequestBody EPrescriptionFrontDTO ePrescriptionFrontDTO) {
    	Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Patient patient = patientService.findById(person.getId());
    	try {
			Pharmacy pharmacy = pharmacyService.findById(ePrescriptionFrontDTO.getPharmacyId());
			try {
				EPrescription ePrescription = ePrescriptionService.findByCode(ePrescriptionFrontDTO.getCode());
				if(ePrescription == null) {
					throw new ResourceConflictException("EPrescription with that code already exists");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			ePrescriptionService.saveEPrescription(patient, ePrescriptionFrontDTO, pharmacy);
			this.emailServices.sendNotificationAsync(patient.getEmail(), "EPrescription INFO", ""
					+ "EPrescription was successfull and you have successfully bought drugs in this pharmacy" + ".");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	 return new ResponseEntity<>("You have successfully bought drugs in this pharmacy!", HttpStatus.CREATED);
	}

	
}
