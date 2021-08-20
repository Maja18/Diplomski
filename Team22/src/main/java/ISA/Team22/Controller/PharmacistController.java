package ISA.Team22.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.DTO.PharmacistDTO;
import ISA.Team22.Domain.DTO.UserInfoComplaintDTO;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Service.PharmacistService;

@RestController
@RequestMapping(value = "/api/pharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {

	private final PharmacistService pharmacistService;

	@Autowired
	public PharmacistController(PharmacistService pharmacistService) {
		this.pharmacistService = pharmacistService;
	}
	
	@GetMapping("/profile")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<PharmacistDTO> getMyAccount() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Pharmacist pharmacist = pharmacistService.getById(person.getId());
		AddressDTO addressDto = new AddressDTO(pharmacist.getAddress().getCity().getName(),
				pharmacist.getAddress().getStreetName(), pharmacist.getAddress().getStreetNumber(),
				pharmacist.getAddress().getCity().getCountry().getName());
		PharmacistDTO pharmacistDTO = new PharmacistDTO(pharmacist.getName(), pharmacist.getLastName(),
				pharmacist.getEmail(), addressDto, pharmacist.getContact());
		return (ResponseEntity<PharmacistDTO>) (pharmacist == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(pharmacistDTO));
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<String> updatePharmacistInfo(@RequestBody PharmacistDTO pharmacistDTO) {
		try {
			pharmacistService.update(pharmacistDTO);
			return new ResponseEntity<>("Profile successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/updatePassword")
	@PreAuthorize("hasRole('PHARMACIST')")
	public ResponseEntity<String> updatePharmacistPassword(@RequestBody PharmacistDTO pharmacistDTO) {
		try {
			String response = pharmacistService.updatePassword(pharmacistDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/pharmacistInfo")
	@PreAuthorize("hasRole('PATIENT')")  
	public ResponseEntity<List<UserInfoComplaintDTO>> getPharmacistInfo() {
	  
		List<UserInfoComplaintDTO> infoComplaintDTOs = new ArrayList<UserInfoComplaintDTO>();
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        for (Pharmacist pharmacist: pharmacists) {
        	UserInfoComplaintDTO userInfoComplaintDTO = new UserInfoComplaintDTO();
        	userInfoComplaintDTO.setFullName(pharmacist.getName() + " " + pharmacist.getLastName());
        	userInfoComplaintDTO.setEmail(pharmacist.getEmail());
        	userInfoComplaintDTO.setUserId(pharmacist.getId());
        	infoComplaintDTOs.add(userInfoComplaintDTO);
        }
        
        return infoComplaintDTOs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(infoComplaintDTOs);
		
	}
	  
		@GetMapping("/myPatients")
		@PreAuthorize("hasRole('PHARMACIST')")
		public ResponseEntity<List<PatientSearchDTO>> getMyPatients() {
			List<PatientSearchDTO> patients = pharmacistService.getMyPatients();
			if(patients.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return	ResponseEntity.ok(patients);
		}
		
		@PostMapping("/searchMyPatient")
		@PreAuthorize("hasRole('PHARMACIST')")
		public ResponseEntity<List<PatientSearchDTO>> searchMyPatient(@RequestBody PatientSearchDTO patientSearchDTO) {
			try {
				List<PatientSearchDTO> searchResult = pharmacistService.searchMyPatient(patientSearchDTO);
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		  
		}
		
		@PostMapping("/sortResult")
		@PreAuthorize("hasRole('PHARMACIST')")
		public ResponseEntity<List<PatientSearchDTO>> sortMyPatient(@RequestBody PatientSearchDTO sortingKey) {
			try {
				List<PatientSearchDTO> searchResult = pharmacistService.sortMyPatient(sortingKey);
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/sortResultDescending")
		@PreAuthorize("hasRole('PHARMACIST')")
		public ResponseEntity<List<PatientSearchDTO>> sortMyPatientDescending(@RequestBody PatientSearchDTO sortingKey) {
			try {
				List<PatientSearchDTO> searchResult = pharmacistService.sortMyPatientDescending(sortingKey);
				return	ResponseEntity.ok(searchResult);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
}
