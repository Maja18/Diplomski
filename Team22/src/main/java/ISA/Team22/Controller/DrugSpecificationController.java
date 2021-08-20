package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Service.DrugSpecificationService;

@RestController
public class DrugSpecificationController {

	@Autowired
	private DrugSpecificationService drugSpecificationService;
}
