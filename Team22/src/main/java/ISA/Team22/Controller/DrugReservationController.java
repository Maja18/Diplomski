package ISA.Team22.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.DrugReservationDTO;
import ISA.Team22.Domain.DTO.ExaminationDTO;
import ISA.Team22.Domain.DrugManipulation.DrugReservation;
import ISA.Team22.Domain.DrugManipulation.DrugStatus;
import ISA.Team22.Service.DrugReservationService;

@RestController
@RequestMapping(value = "/api/drugReservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DrugReservationController {

	private final DrugReservationService drugReservationService;
	
	@Autowired
	public DrugReservationController(DrugReservationService drugReservationService) {
		super();
		this.drugReservationService = drugReservationService;
	}
	
	@GetMapping("/getReservation/{id}")
	@PreAuthorize("hasRole('PHARMACIST')")  
	public ResponseEntity<DrugReservationDTO> getAllComplaints(@PathVariable Integer id){
		Long idr = (long) id;
		DrugReservationDTO response = drugReservationService.getDrugReservation(idr);
		return response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response);
	}
	
	@PostMapping("/updateReservation")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<DrugStatus> updateReservation(@RequestBody DrugReservationDTO drugReservationDTO) {
		try {
			drugReservationService.updateReservation(drugReservationDTO.getId());
			DrugStatus response = DrugStatus.taken;
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
