package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Repository.DrugSpecificationRepository;
import ISA.Team22.Service.IService.IDrugSpecificationService;

@Service
public class DrugSpecificationService implements IDrugSpecificationService {

	@Autowired
	private  DrugSpecificationRepository drugSpecificationRepository;
}
