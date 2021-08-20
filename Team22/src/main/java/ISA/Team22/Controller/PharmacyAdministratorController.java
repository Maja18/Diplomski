package ISA.Team22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Service.PersonService;
import ISA.Team22.Service.PharmacyAdministratorService;

@RestController
@RequestMapping("/api/pharmacyAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyAdministratorController {

	private final PharmacyAdministratorService pharmacyAdministratorService;
	private final PersonService personService;
	
	public PharmacyAdministratorController(PharmacyAdministratorService pharmacyAdministratorService,PersonService personService) {
		this.pharmacyAdministratorService = pharmacyAdministratorService;
		this.personService = personService;
	}
	
	 @PostMapping("/register")
	 @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
	 public ResponseEntity<String> registerPharmacyAdmin(@RequestBody PersonRequestDTO userRequest) {
       if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
           throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
       }
       Person existingUser = personService.findByEmail(userRequest.getEmail());
       if (existingUser != null) {
           throw new IllegalArgumentException("Entered email already exists");
       }

       Person user = pharmacyAdministratorService.save(userRequest);
       return new ResponseEntity<>("Pharmacy administrator is successfully registred!", HttpStatus.CREATED);
   }

}
