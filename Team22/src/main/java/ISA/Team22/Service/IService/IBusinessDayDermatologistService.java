package ISA.Team22.Service.IService;

import java.time.LocalDate;
import java.util.List;

import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;


public interface IBusinessDayDermatologistService {
	
	//List<BusinessDayDermatologist> getDermatolgistBusinessDay(Long dermatologistID, LocalDate date);
	BusinessDayDermatologist getDermatolgistBusinessDay(Long dermatologist_id, LocalDate date, Long pharmacyID);
}
