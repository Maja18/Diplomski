package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.AbsenceRequestDTO;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.Period;
import ISA.Team22.Domain.PharmacyWorkflow.VacationType;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.AbsenceRequestDermatologistRepository;
import ISA.Team22.Service.IService.IAbsenceRequestDermatologistService;

@Service
public class AbsenceRequestDermatologistService implements IAbsenceRequestDermatologistService {

	private final AbsenceRequestDermatologistRepository absenceRequestDermatologistRepository;
	private final DermatologistService dermatologistSrevice;
	
	@Autowired
	public AbsenceRequestDermatologistService(
			AbsenceRequestDermatologistRepository absenceRequestDermatologistRepository, DermatologistService dermatologistSrevice) {
		super();
		this.absenceRequestDermatologistRepository = absenceRequestDermatologistRepository;
		this.dermatologistSrevice = dermatologistSrevice;
	}

	@Override
	public void setRequest(AbsenceRequestDTO absenceRequestDTO) {
		AbsenceRequestDermatologist request = new AbsenceRequestDermatologist();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Dermatologist dermatologist = dermatologistSrevice.getById(person.getId());
		request.setDermatologist(dermatologist);
		request.setStartDate(absenceRequestDTO.getStartDate());
		request.setEndDate(absenceRequestDTO.getEndDate());
		request.setVacationType(absenceRequestDTO.getVacationType());
		absenceRequestDermatologistRepository.save(request);
	}
}
