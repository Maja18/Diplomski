package ISA.Team22.Controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.AbsenceRequestDTO;
import ISA.Team22.Domain.PharmacyWorkflow.VacationType;
import ISA.Team22.Service.AbsenceRequestDermatologistService;

@RestController
@RequestMapping(value = "/api/absenceDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
public class AbsenceRequestDermatologistController {
	
	
	private final AbsenceRequestDermatologistService absenceRequestDermatologistService;

	@Autowired
	public AbsenceRequestDermatologistController(
			AbsenceRequestDermatologistService absenceRequestDermatologistService) {
		super();
		this.absenceRequestDermatologistService = absenceRequestDermatologistService;
	}
	
	@GetMapping("/absenceType")
	@PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<VacationType>> getVacationTypes() {
		try {
			List<VacationType> vacationTypes = new ArrayList<VacationType>(EnumSet.allOf(VacationType.class));
			return	ResponseEntity.ok(vacationTypes);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/sendRequest")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<String> setRequest(@RequestBody AbsenceRequestDTO absenceRequestDTO) {
		try {
			absenceRequestDermatologistService.setRequest(absenceRequestDTO);
			return	ResponseEntity.ok("Absenece request is sent.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
