package ISA.Team22.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.DermatologistDTO;
import ISA.Team22.Domain.DTO.UserInfoComplaintDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Service.DermatologistService;
import ISA.Team22.Service.PersonService;

@RestController
@RequestMapping(value = "/api/dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DermatologistController {

	private final DermatologistService dermatologistService;
	private final PersonService personService;

	@Autowired
	public DermatologistController(DermatologistService dermatologistService,PersonService personService) {
		this.dermatologistService = dermatologistService;
		this.personService = personService;
	}

	@GetMapping("/profile")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<DermatologistDTO> getMyAccount() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Dermatologist dermatologist = dermatologistService.getById(person.getId());
		AddressDTO addressDto = new AddressDTO(dermatologist.getAddress().getCity().getName(),
				dermatologist.getAddress().getStreetName(), dermatologist.getAddress().getStreetNumber(),
				dermatologist.getAddress().getCity().getCountry().getName());
		DermatologistDTO dermatologistDTO = new DermatologistDTO(dermatologist.getName(), dermatologist.getLastName(),
				dermatologist.getEmail(), addressDto, dermatologist.getContact());
		return (ResponseEntity<DermatologistDTO>) (dermatologist == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(dermatologistDTO));
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<String> updateDermatologistInfo(@RequestBody DermatologistDTO dermatologistDTO) {
		try {
			dermatologistService.update(dermatologistDTO);
			return new ResponseEntity<>("Profile successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/updatePassword")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<String> updateDermatologistPassword(@RequestBody DermatologistDTO dermatologistDTO) {
		try {
			String response = dermatologistService.updatePassword(dermatologistDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}

	  @PostMapping("/register")
	  @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
	  public ResponseEntity<String> registerDermatologist(@RequestBody DermatologistDTO userRequest) {
		    Person existingUser = personService.findByEmail(userRequest.getEmail());
	        if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
	            throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
	        }
	        if (existingUser != null) {
	            throw new IllegalArgumentException("Entered email already exists");
	        }
	        Person person = dermatologistService.save(userRequest);
	        
	        return new ResponseEntity<>("Dermatologist is successfully registred!", HttpStatus.CREATED);
	    }
	  
	@GetMapping("/dermaInfo")
	@PreAuthorize("hasRole('PATIENT')")  
	public ResponseEntity<List<UserInfoComplaintDTO>> getDermatologyInfo() {
		  
		List<UserInfoComplaintDTO> infoComplaintDTOs = new ArrayList<UserInfoComplaintDTO>();
	    List<Dermatologist> dermatologists = dermatologistService.findAll();
	    for (Dermatologist dermatologist: dermatologists) {
	      	UserInfoComplaintDTO userInfoComplaintDTO = new UserInfoComplaintDTO();
	       	userInfoComplaintDTO.setFullName(dermatologist.getName() + " " + dermatologist.getLastName());
	       	userInfoComplaintDTO.setEmail(dermatologist.getEmail());
        	userInfoComplaintDTO.setUserId(dermatologist.getId());
	       	infoComplaintDTOs.add(userInfoComplaintDTO);
	    }
	        
	    return infoComplaintDTOs == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(infoComplaintDTOs);
			
		}

	@GetMapping("/myPatients")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<PatientSearchDTO>> getMyPatients() {
		List<PatientSearchDTO> patients = dermatologistService.getMyPatients();
		if(patients.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return	ResponseEntity.ok(patients);
	}
	
	@PostMapping("/searchMyPatient")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<PatientSearchDTO>> searchMyPatient(@RequestBody PatientSearchDTO patientSearchDTO) {
		try {
			List<PatientSearchDTO> searchResult = dermatologistService.searchMyPatient(patientSearchDTO);
			return	ResponseEntity.ok(searchResult);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/sortResult")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<PatientSearchDTO>> sortMyPatient(@RequestBody PatientSearchDTO sortingKey) {
		try {
			List<PatientSearchDTO> searchResult = dermatologistService.sortMyPatient(sortingKey);
			return	ResponseEntity.ok(searchResult);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/sortResultDescending")
	@PreAuthorize("hasRole('DERMATOLOGIST')")
	public ResponseEntity<List<PatientSearchDTO>> sortMyPatientDescending(@RequestBody PatientSearchDTO sortingKey) {
		try {
			List<PatientSearchDTO> searchResult = dermatologistService.sortMyPatientDescending(sortingKey);
			return	ResponseEntity.ok(searchResult);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
