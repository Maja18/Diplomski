package ISA.Team22.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;
import ISA.Team22.Repository.BusinessDayDermatologistRepository;
import ISA.Team22.Service.IService.IBusinessDayDermatologistService;

@Service
public class BusinessDayDermatologistService implements IBusinessDayDermatologistService {


	private final  BusinessDayDermatologistRepository businessDayDermatologistRepository;
	
	@Autowired
	public BusinessDayDermatologistService(BusinessDayDermatologistRepository businessDayDermatologistRepository) {
		super();
		this.businessDayDermatologistRepository = businessDayDermatologistRepository;
	}

	/*@Override
	public List<BusinessDayDermatologist> getDermatolgistBusinessDay(Long dermatologist_id, LocalDate date) {
		return businessDayDermatologistRepository.getAllPharmacyDermatolgist(dermatologist_id, date);
	}*/
	
	public BusinessDayDermatologist getDermatolgistBusinessDay(Long dermatologistID, LocalDate date, Long pharmacyID) {
		List<BusinessDayDermatologist> allBusinessDay =  businessDayDermatologistRepository.findAll();
		BusinessDayDermatologist specificDermatologistBusinessDay = new BusinessDayDermatologist();
		
		for(BusinessDayDermatologist a:allBusinessDay)
			if(a.getDermatologist().getId() == dermatologistID && a.getPharmacyId() == pharmacyID)
				if(a.getShift().getStartDate().equals(date))
					specificDermatologistBusinessDay = a;
		return specificDermatologistBusinessDay;
	}
	
}
