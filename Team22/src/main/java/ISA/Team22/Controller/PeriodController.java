package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Service.PeriodService;

@RestController
public class PeriodController {

	@Autowired
	private PeriodService periodService;
}
