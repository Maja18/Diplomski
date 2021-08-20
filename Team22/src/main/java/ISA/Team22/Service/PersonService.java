package ISA.Team22.Service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.PersonRepository;
import ISA.Team22.Service.IService.IPersonService;

@Service
public class PersonService implements IPersonService, UserDetailsService {
	
	protected final Log LOGGER = LogFactory.getLog(getClass());
   
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthorityService authService;


	@Override
	public Person findById(Long id) {
		Person p = personRepository.findById(id).orElseGet(null);
		return p;
	}

	@Override
	public Person findByEmail(String email) {
		Person p = personRepository.findByEmail(email);
		return p;
	}

	@Override
	public List<Person> findAll() {
		List<Person> result = personRepository.findAll();
		return result;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //email ne username!
		//kako hocemo da nam dobavi korisnika spring security
		Person person = personRepository.findByEmail(email);
		if (person == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
		} else {
			return person;
		}
	}

	public void changePassword(String oldPassword, String newPassword) {

		// Ocitavamo trenutno ulogovanog korisnika sa pvpm dole linijom
		Authentication currentPerson = SecurityContextHolder.getContext().getAuthentication();
		String username = currentPerson.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		Person person = (Person) loadUserByUsername(username);

		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		person.setPassword(passwordEncoder.encode(newPassword));
		personRepository.save(person);

	}
	@Override
	public Person save(PersonRequestDTO personRequest) {
		Person u = new Person() {
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return this.getEmail();
			}
		};
        u.setEmail(personRequest.getEmail());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(personRequest.getPassword()));
        u.setName(personRequest.getName());
        u.setLastName(personRequest.getSurname());
        u.setEnabled(true);
        City city = new City();
        city.setName(personRequest.getAddress().getTown());
        Country country = new Country();
        country.setName(personRequest.getAddress().getCountry());
        city.setCountry(country);
        Address address = new Address( personRequest.getAddress().getStreet(), personRequest.getAddress().getNumber(),city);
        u.setAddress(address);
        u.setFirstLogged(true);
        u.setContact(personRequest.getPhoneNumber());

        Authority auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        //u.setAuthorities(auth);

        u = this.personRepository.save(u);
        return u;
	}
	
	@Override
	 public void changePasswordFirstLogin(String oldPassword, String newPassword) {
	        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
	        Person user = (Person)currentUser.getPrincipal();
	        user.setPassword(passwordEncoder.encode(newPassword));
	        user.setFirstLogged(false);
	        personRepository.save(user);
	    }
	 @Override
		public Person savePerson(Person person) {
			return personRepository.save(person);
		}
	
}
