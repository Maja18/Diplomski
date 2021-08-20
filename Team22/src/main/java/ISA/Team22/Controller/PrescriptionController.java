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

import ISA.Team22.Domain.DTO.PrescriptionDTO;
import ISA.Team22.Service.PrescriptionService;

@RestController
@RequestMapping(value = "/api/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescriptionController {
	
	private final PrescriptionService  prescriptionService;

	@Autowired
	public PrescriptionController(PrescriptionService prescriptionService) {
		super();
		this.prescriptionService = prescriptionService;
	}
	
	
	@PostMapping("/prescribe")
	@PreAuthorize("hasRole('PHARMACIST')" + "||" + "hasRole('DERMATOLOGIST')")
    public ResponseEntity<String> prescribeDrug( @RequestBody PrescriptionDTO prescriptionDTO){
		 
		try {
			prescriptionService.prescribeDrug(prescriptionDTO);
			return new ResponseEntity<>("Prescription is created!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
    }
	
	
}
