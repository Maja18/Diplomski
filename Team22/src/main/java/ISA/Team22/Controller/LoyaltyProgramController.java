package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.LoaltyProgramDTO;
import ISA.Team22.Domain.PharmacyWorkflow.LoyaltyProgram;
import ISA.Team22.Service.LoyaltyProgramService;

@RestController
@RequestMapping(value = "/api/loyaltyProgram", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoyaltyProgramController {
	
	private final LoyaltyProgramService loyaltyService;
	
	@Autowired
	public LoyaltyProgramController(LoyaltyProgramService loyaltyService) {
		this.loyaltyService = loyaltyService;
		
	}
	
	@PostMapping("/defineLoaltyProgram")
	@PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
     public ResponseEntity<String> defineLoaltyProgram(@RequestBody LoaltyProgramDTO loaltyProgramDTO ) 
	{
		   
	    LoyaltyProgram loaltyProgram = loyaltyService.save(loaltyProgramDTO);
	     
	    return loaltyProgram == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Loalty program is successfully defined!", HttpStatus.CREATED);
	 }
	
}
