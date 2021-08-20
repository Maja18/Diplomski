package ISA.Team22.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayPharmacist;
import ISA.Team22.Repository.BusinessDayPharmacistRepository;
import ISA.Team22.Service.IService.IBusinessDayPharmacistService;


@Service
public class BusinessDayPharmacistService implements IBusinessDayPharmacistService  {

	private final BusinessDayPharmacistRepository businessDayPharmacistRepository;

	@Autowired
	public BusinessDayPharmacistService(BusinessDayPharmacistRepository businessDayPharmacistRepository) {
		super();
		this.businessDayPharmacistRepository = businessDayPharmacistRepository;
	}
	
	@Override
	public BusinessDayPharmacist getPharmacisttBusinessDay(Long dermatologistID, LocalDate date) {
		List<BusinessDayPharmacist> allBusinessDay =  businessDayPharmacistRepository.findAll();
		BusinessDayPharmacist specificPharmacistBusinessDay = new BusinessDayPharmacist();
		
		for(BusinessDayPharmacist a:allBusinessDay)
			if(a.getPharmacist().getId() == dermatologistID)
				if(a.getShift().getStartDate().equals(date))
					specificPharmacistBusinessDay = a;
		return specificPharmacistBusinessDay;
	}
	
}
