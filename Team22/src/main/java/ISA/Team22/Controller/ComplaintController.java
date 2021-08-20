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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.ComplaintDTO;
import ISA.Team22.Domain.DTO.ComplaintReviewDTO;
import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.Complaint;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.PatientRepository;
import ISA.Team22.Service.ComplaintService;
import ISA.Team22.Service.CounselingService;
import ISA.Team22.Service.DermatologistService;
import ISA.Team22.Service.ExaminationService;
import ISA.Team22.Service.PersonService;
import ISA.Team22.Service.PharmacyService;

@RestController
@RequestMapping("/api/complaint")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintController {
	
	private final ComplaintService complaintService;
	private final PersonService personService;
	private final ExaminationService examinationService;
	private final CounselingService counselingService;
	private final PharmacyService pharmacyService;
	
	
	@Autowired
	public ComplaintController(ComplaintService complaintService,PersonService personService,ExaminationService examinationService,CounselingService counselingService,PharmacyService pharmacyService) {
		this.complaintService = complaintService;
		this.personService = personService;
		this.examinationService = examinationService;
		this.counselingService = counselingService;
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping("/addComplaintDermatologist")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Complaint> addComplaintDermatologist(@RequestBody ComplaintDTO complaintDTO)
    {
        try {
        	Dermatologist dermatologist =(Dermatologist) personService.findById(complaintDTO.getDermatologyId());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
        if(!examinationService.canDermatologistMakeComplaint(complaintDTO.getDermatologyId())) {
            throw new IllegalArgumentException("You aren't able to write complaint to this dermatologist!");
        }
        
        
        Complaint complaint = complaintService.save(complaintDTO);
        
            return complaint == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(complaint);
    }
	
	@PostMapping("/addComplaintPharmacist")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Complaint> addComplaintPharmacist(@RequestBody ComplaintDTO complaintDTO)
    {
        try {
        	Pharmacist pharmacist =(Pharmacist) personService.findById(complaintDTO.getPharmacistId());
            System.out.println(pharmacist.getName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
        if(!counselingService.canPharmacistMakeComplaint(complaintDTO.getPharmacistId())) {
            throw new IllegalArgumentException("You aren't able to write complaint to this pharmacist!");
        }
        
        Complaint complaint = complaintService.save(complaintDTO);
        
            return complaint == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(complaint);
    }
	
	@PostMapping("/addComplaintPharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Complaint> addComplaintPharmacy(@RequestBody ComplaintDTO complaintDTO)
    {
        try {
        	Pharmacy pharmacy =(Pharmacy) pharmacyService.findById(complaintDTO.getPharmacyId());
            System.out.println(pharmacy.getName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
        if(!pharmacyService.canMakeComplaintPharmacy(complaintDTO.getPharmacyId())) {
            throw new IllegalArgumentException("You aren't able to write complaint to this pharmacy!");
        }
        
        
        Complaint complaint = complaintService.save(complaintDTO);
        
            return complaint == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(complaint);
    }
	
	@GetMapping("/complaints")
	@PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")  
	public ResponseEntity<List<ComplaintReviewDTO>> getAllComplaints() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		List<Complaint> complaints = complaintService.findAll();
		List<ComplaintReviewDTO> complaintDTOs = new ArrayList<ComplaintReviewDTO>();
		
		complaintService.findComplaints(complaints, complaintDTOs);
		
		 return complaintDTOs == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(complaintDTOs);
	}
	
	@PostMapping("/sendAnswer")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<Complaint> sendAnswerOnComplaint(@RequestBody ComplaintReviewDTO complaintReviewDTO)
    {
		Complaint complaintUpdated = new Complaint();
        try {
        	complaintUpdated = complaintService.updateComplaint(complaintReviewDTO);
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("error");
		}
        
        return complaintUpdated == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaintUpdated);
    }
   

}
