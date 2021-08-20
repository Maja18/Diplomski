package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.AbsenceRequestDTO;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.AbsenceRequestPharmacistRepository;
import ISA.Team22.Service.IService.IAbsenceRequestPharmacistService;

@Service
public class AbsenceRequestPharmacistService implements IAbsenceRequestPharmacistService{
	
	private final AbsenceRequestPharmacistRepository absenceRequestPharmacistRepository;
	private final PharmacistService pharmacistService;
	
	@Autowired
	public AbsenceRequestPharmacistService(AbsenceRequestPharmacistRepository absenceRequestPharmacistRepository,
			PharmacistService pharmacistService) {
		super();
		this.absenceRequestPharmacistRepository = absenceRequestPharmacistRepository;
		this.pharmacistService = pharmacistService;
	}




	@Override
	public void setRequest(AbsenceRequestDTO absenceRequestDTO) {
		AbsenceRequestPharmacist request = new AbsenceRequestPharmacist();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Pharmacist pharmacist = pharmacistService.getById(person.getId());
		request.setPharmacist(pharmacist);
		request.setStartDate(absenceRequestDTO.getStartDate());
		request.setEndDate(absenceRequestDTO.getEndDate());
		request.setVacationType(absenceRequestDTO.getVacationType());
		absenceRequestPharmacistRepository.save(request);
	}
}
