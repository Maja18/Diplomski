package ISA.Team22.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.CounselingDTO;
import ISA.Team22.Domain.DTO.DataForCalendarDTO;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Service.CounselingService;

@RestController
@RequestMapping(value = "/api/counseling", produces = MediaType.APPLICATION_JSON_VALUE)
public class CounselingController {

	
	private final CounselingService counselingService;
	
	@Autowired
	public CounselingController(CounselingService counselingService) {
		super();
		this.counselingService = counselingService;
	}
	
	
	@PostMapping("/schedule")
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public ResponseEntity<String> scheduleCounselling(@RequestBody CounselingDTO counselingDTO) {
		try {
			String response = counselingService.scheduleCounselling(counselingDTO);
			System.out.println(response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getMyScheduledCounselings")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<List<CounselingDTO>> getMyScheduledCounsellings(){
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		List<CounselingDTO> examinationsDTO = counselingService.getMyScheduledCounsellings(person.getId());
		
		return examinationsDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(examinationsDTO);
	}
	
	@GetMapping("/getCounselingById/{id}")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<CounselingDTO> getCounsellingById(@PathVariable Integer id){
		try {
			Long idr = (long) id;
			CounselingDTO response = counselingService.getCounsellingById(idr);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/absenceRegister")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<String> registerAbsence(@RequestBody CounselingDTO counselingDTO) {
		try {
			counselingService.registerAbsence(counselingDTO.getCounselingId());
			return new ResponseEntity<>("Examination absence registered.", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/updateCounseling")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<String> updateCounseling(@RequestBody CounselingDTO counselingDTO) {
		try {
			counselingService.updateCounseling(counselingDTO);
			return new ResponseEntity<>("Counseling finished.", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getCounselingsForCalendar")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<List<DataForCalendarDTO>> getCounselingsForCalendar(){
		try {
			List<DataForCalendarDTO> response = counselingService.getCounselingsForCalendar();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

}
