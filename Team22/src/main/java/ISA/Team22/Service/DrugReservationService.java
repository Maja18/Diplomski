package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.DrugDTO;
import ISA.Team22.Domain.DTO.DrugReservationDTO;
import ISA.Team22.Domain.DTO.PatientFrontDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugReservation;
import ISA.Team22.Domain.DrugManipulation.DrugStatus;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.DrugReservationRepository;
import ISA.Team22.Service.IService.IDrugReservationService;

@Service
public class DrugReservationService implements IDrugReservationService {


	private final DrugReservationRepository drugReservationRepository;
	private final DrugService drugService;
	private final PharmacistService pharmacistService;
	private final EmailService emailServices;

	@Autowired
	public DrugReservationService(DrugReservationRepository drugReservationRepository, DrugService drugService,
			PharmacistService pharmacistService, EmailService emailServices) {
		super();
		this.drugReservationRepository = drugReservationRepository;
		this.drugService = drugService;
		this.pharmacistService = pharmacistService;
		this.emailServices = emailServices;
	}

	@Override
	public DrugReservationDTO getDrugReservation(Long id) {
		DrugReservationDTO drugReservationDTO = new DrugReservationDTO();
		DrugReservation drugRes = new DrugReservation();
		Boolean isPresentReservation = drugReservationRepository.findById(id).isPresent();
		if(isPresentReservation) {
			drugRes = drugReservationRepository.findById(id).get();
			Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
			Person person = (Person) currentUser.getPrincipal();
			Pharmacist pharmacist = pharmacistService.getById(person.getId());
			if(drugRes != null && drugRes.getPharmacyId().equals(pharmacist.getPharmacy().getId()) && drugRes.getDueDate().isAfter(drugRes.getDueDate().minusDays(1))) {
				PatientFrontDTO patient = new PatientFrontDTO(drugRes.getPatient().getId(), drugRes.getPatient().getName(), 
						drugRes.getPatient().getLastName(), drugRes.getPatient().getEmail());
				Drug drug = drugService.findById(drugRes.getDrugId());
				DrugDTO drugDto = new DrugDTO(drugRes.getDrugId(), drug.getName());
				drugReservationDTO = new DrugReservationDTO(drugRes.getResevationId(), drugRes.getStatus(), drugRes.getDueDate(), patient,
						drugRes.getPharmacyId(), drugDto);
			}
		}
		return drugReservationDTO;
	}

	@Override
	public void updateReservation(Long id) {
		DrugReservation drugReservation = drugReservationRepository.findById(id).get();
		drugReservation.setStatus(DrugStatus.taken);
		
		drugReservationRepository.save(drugReservation);
		this.emailServices.sendNotificationAsync(drugReservation.getPatient().getEmail(), "Drug reservation INFO", ""
				+ "We are confirming that you have sucessfuli took your reserved drug. ");
		
		
	}
}
