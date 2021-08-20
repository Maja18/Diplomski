package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Service.BusinessDayPharmacistService;

@RestController
public class BusinessDayPharmacistController {

	@Autowired
	private BusinessDayPharmacistService  businessDayPharmacistService;
}
