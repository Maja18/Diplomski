package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.AbsenceRequestDTO;
import ISA.Team22.Service.AbsenceRequestPharmacistService;

@RestController
@RequestMapping(value = "/api/absencePharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
public class AbsenceRequestPharmacistController {

	
	private final AbsenceRequestPharmacistService absenceRequestPharmacistService;
	
	@Autowired
	public AbsenceRequestPharmacistController(AbsenceRequestPharmacistService absenceRequestPharmacistService) {
		super();
		this.absenceRequestPharmacistService = absenceRequestPharmacistService;
	}
	
	@PostMapping("/sendRequest")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<String> setRequest(@RequestBody AbsenceRequestDTO absenceRequestDTO) {
		try {
			absenceRequestPharmacistService.setRequest(absenceRequestDTO);
			return	ResponseEntity.ok("Absenece request is sent.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
