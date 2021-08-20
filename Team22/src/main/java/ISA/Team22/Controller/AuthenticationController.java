package ISA.Team22.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.Team22.Domain.DTO.JwtAuthenticationRequest;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.DTO.UserTokenState;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Exception.ResourceConflictException;
import ISA.Team22.Security.TokenUtils;
import ISA.Team22.Service.EmailService;
import ISA.Team22.Service.PatientService;
import ISA.Team22.Service.PersonService;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	private final TokenUtils tokenUtils;

	private final AuthenticationManager authenticationManager;
	
	private final PersonService personService;
	
	private final PatientService patientService;
	
	private final EmailService emailService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthenticationController(TokenUtils tokenUtils,AuthenticationManager authenticationManager,PersonService personService,
			PatientService patientService,EmailService emailService,PasswordEncoder passwordEncoder) {
		this.tokenUtils = tokenUtils;
		this.authenticationManager = authenticationManager;
		this.personService = personService;
		this.patientService = patientService;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
						authenticationRequest.getPassword()));  
       
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Person person = (Person) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(person.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody PersonRequestDTO userRequest, UriComponentsBuilder ucBuilder) {
		
       if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
        throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
       }
       Person existingUser = personService.findByEmail(userRequest.getEmail());
       if (existingUser != null) {
        throw new ResourceConflictException("Entered email already exists");
       }
       Person user = patientService.save(userRequest);
    
       HttpHeaders headers = new HttpHeaders();
	   headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
	   this.emailService.sendNotificationAsync(user.getEmail(), "Account Validation", "Click this link and validate your account: http://localhost:8085/api/auth/validateAccount/" + user.getId() + "/");
	
       return new ResponseEntity<>("User is successfully registred!", HttpStatus.CREATED);
	}
	
	@GetMapping("/validateAccount/{id}")
	public String validatePatient(@PathVariable("id") Long id) {
		Person person = this.personService.findById(id);
		if(person  == null)
			return "Bad Request!";
		person .setEnabled(true);
		this.personService.savePerson(person);
		
		return "Validation succesfull, now you can use your account. Please return to login page.";
	}


	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		personService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}
	
	@RequestMapping(value = "/passwordFirstLogin", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMIN', 'PHARMACIST')")
    public ResponseEntity<?> changePasswordFirstLogin(@RequestBody PasswordChanger passwordChanger) {
		 Person person = personService.findByEmail(passwordChanger.email);
		 
        if(passwordChanger.newPassword.isEmpty() || passwordChanger.rewriteNewPassword.isEmpty()|| passwordChanger.oldPassword.isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(!passwordChanger.newPassword.equals(passwordChanger.rewriteNewPassword)) {
            throw new IllegalArgumentException("Please make sure your new password and rewrite password match!");
        }
        if(passwordChanger.newPassword.equals(passwordChanger.oldPassword)) {
            throw new IllegalArgumentException("New password can not be same as old password!");
        }
        if(!(passwordEncoder.matches(passwordChanger.oldPassword,person.getPassword()))) {
        	throw new IllegalArgumentException("Please enter valid current password!");
        }
        
        personService.changePasswordFirstLogin(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }
	
	@GetMapping("/authority")
    @PreAuthorize("hasAnyRole('PATIENT', 'SUPPLIER', 'SYSTEM_ADMINISTRATOR', 'DERMATOLOGIST', 'PHARMACY_ADMINISTRATOR', 'PHARMACIST')")
    ResponseEntity<Person> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        
        Person user = (Person)currentUser.getPrincipal();
        Person userWithId = personService.findById(user.getId());

        return (ResponseEntity<Person>) (userWithId == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(userWithId));
    }

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
		public String rewriteNewPassword;
		public String email;
	}
}
