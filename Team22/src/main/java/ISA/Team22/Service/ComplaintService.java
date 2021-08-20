package ISA.Team22.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Controller.ComplaintController;
import ISA.Team22.Domain.DTO.ComplaintDTO;
import ISA.Team22.Domain.DTO.ComplaintReviewDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.Complaint;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.ComplaintRepository;
import ISA.Team22.Repository.DermatologistRepository;
import ISA.Team22.Repository.PatientRepository;
import ISA.Team22.Repository.PharmacistRepository;
import ISA.Team22.Repository.PharmacyRepository;
import ISA.Team22.Service.IService.IComplaintService;

@Service
public class ComplaintService implements IComplaintService {

	private final ComplaintRepository complaintRepository;
	private final PatientRepository patientRepository;
	private final DermatologistRepository dermatologistRepository;
	private final PharmacistRepository pharmacistRepository;
	private final PharmacyRepository pharmacyRepository;
	private final EmailService emailServices;
	
	@Autowired
	public ComplaintService(ComplaintRepository complaintRepository,PatientRepository patientRepository,
			DermatologistRepository dermatologistRepository,PharmacistRepository pharmacistRepository,PharmacyRepository pharmacyRepository,EmailService emailServices) {
		this.complaintRepository = complaintRepository;
		this.patientRepository = patientRepository;
		this.dermatologistRepository = dermatologistRepository;
		this.pharmacistRepository = pharmacistRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.emailServices = emailServices;
	}
	
	@Override
	public Complaint save(ComplaintDTO complaintDTO) {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(person.getId()).get();
        
		Complaint complaint = new Complaint();
		complaint.setId(complaintDTO.getId());
		complaint.setDescription(complaintDTO.getDescription());
        complaint.setAnswer(complaintDTO.getAnswer());
        complaint.setAnswered(complaintDTO.getIsAnswered());
        complaint.setPatient(patient);
        
        if(complaintDTO.getDermatologyId()==null) {
        	complaint.setDermatologist(null);
        }else {
        	Dermatologist dermatologist = dermatologistRepository.findById(complaintDTO.getDermatologyId()).get();
        	complaint.setDermatologist(dermatologist);
        	complaint.setRole("dermatologist");
        }
        
        if(complaintDTO.getPharmacistId()==null) {
        	complaint.setPharmacist(null);
        }else {
        	Pharmacist pharmacist = pharmacistRepository.findById(complaintDTO.getPharmacistId()).get();
        	complaint.setPharmacist(pharmacist);
        	complaint.setRole("pharmacist");
        }
        
        if(complaintDTO.getPharmacyId()==null) {
        	complaint.setPharmacy(null);
        }else {
        	Pharmacy pharmacy = pharmacyRepository.findById(complaintDTO.getPharmacyId()).get();
        	complaint.setPharmacy(pharmacy);
        	complaint.setRole("pharmacy");
        }
       
        
        return complaintRepository.save(complaint);
	}



	@Override
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	
	@Override
	public void findComplaints(List<Complaint> complaints, List<ComplaintReviewDTO> complaintDTOs) {
		for(Complaint c:complaints) {
			if(!c.isAnswered()) {
			ComplaintReviewDTO complaintDTO = new ComplaintReviewDTO();
			complaintDTO.setId(c.getId());
			complaintDTO.setNameFrom(c.getPatient().getName());
			complaintDTO.setDescription(c.getDescription());
			
			if(c.getRole().equals("dermatologist")){
				complaintDTO.setNameTo(c.getDermatologist().getName());
			}else if(c.getRole().equals("pharmacist")) {
				complaintDTO.setNameTo(c.getPharmacist().getName());
			}else {
				complaintDTO.setNameTo(c.getPharmacy().getName());
			}
			complaintDTOs.add(complaintDTO);
			}
		}
	}
	
	@Override
	public Complaint updateComplaint(ComplaintReviewDTO complaintReviewDTO) {
		Complaint complaint = complaintRepository.findById(complaintReviewDTO.getId()).get();
		complaint.setAnswered(true);
		complaint.setAnswer(complaintReviewDTO.getAnswer());
		Patient patient = complaint.getPatient();
		this.emailServices.sendNotificationAsync(patient.getEmail(), "Complaint  INFO", ""
				 + complaint.getAnswer().toString());
		
		return complaintRepository.save(complaint);
	}
}
