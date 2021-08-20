package ISA.Team22.Controller;



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
import ISA.Team22.Domain.DTO.DermatologistDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.DTO.SupplierDTO;

import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;

import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Service.PersonService;
import ISA.Team22.Service.SupplierService;

@RestController
@RequestMapping(value = "/api/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {

	private final SupplierService supplierService;
	private final PersonService personService;

	@Autowired
	public SupplierController(SupplierService supplierService,PersonService personService) {
		this.supplierService = supplierService;
		this.personService = personService;
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
     public ResponseEntity<String> registerSupplier(@RequestBody PersonRequestDTO userRequest) 
	{
		   
	     if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
	         throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
	     }
	     Person existingUser = personService.findByEmail(userRequest.getEmail());
	     if (existingUser != null) {
	          throw new IllegalArgumentException("Entered email already exists");
	     }
	     Person person = supplierService.save(userRequest);
	     
	     return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
	 }
	
	@GetMapping("/profile")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<SupplierDTO> getMyAccount() {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Supplier supplier = supplierService.findById(person.getId());
		AddressDTO addressDto = new AddressDTO(supplier.getAddress().getCity().getName(),
				supplier.getAddress().getStreetName(), supplier.getAddress().getStreetNumber(),
				supplier.getAddress().getCity().getCountry().getName());
		SupplierDTO supplierDTO = new SupplierDTO(supplier.getName(), supplier.getLastName(),
				supplier.getEmail(), addressDto, supplier.getPassword());
		
		return (ResponseEntity<SupplierDTO>) (supplier == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(supplierDTO));
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<String> updateInfo(@RequestBody SupplierDTO supplierDTO) {
		try {
			supplierService.update(supplierDTO);
			return new ResponseEntity<>("Profile successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/updatePassword")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<String> updatePassword(@RequestBody SupplierDTO supplierDTO) {
		try {
			supplierService.updatePassword(supplierDTO);
			return new ResponseEntity<>("Profile successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
}
