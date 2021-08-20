package ISA.Team22.Service.IService;

import java.time.LocalDate;

import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayPharmacist;

public interface IBusinessDayPharmacistService {
	
	BusinessDayPharmacist getPharmacisttBusinessDay(Long dermatologistID, LocalDate date);

}
