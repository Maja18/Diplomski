package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Repository.PeriodRepository;
import ISA.Team22.Service.IService.IPeriodService;

@Service
public class PeriodService implements IPeriodService {

	@Autowired
	private  PeriodRepository periodRepository;
}
