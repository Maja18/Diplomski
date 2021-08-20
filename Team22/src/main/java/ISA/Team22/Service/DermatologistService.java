package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.DermatologistDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Repository.DermatologistRepository;
import ISA.Team22.Service.IService.IDermatologistService;

@Service
public class DermatologistService implements IDermatologistService {

	private final DermatologistRepository dermatologistRepository;
    private final AuthorityService authService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public DermatologistService(DermatologistRepository dermatologistRepository, AuthorityService authService, AuthorityRepository authorityRepository,
			PasswordEncoder passwordEncoder) {
		
		this.dermatologistRepository = dermatologistRepository;
		this.authService = authService;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Dermatologist findByEmail(String email) {
		return dermatologistRepository.findByEmail(email);
	}

	@Override
	public Dermatologist save(DermatologistDTO userRequest) {
		Dermatologist dermatologist =  new Dermatologist();
        dermatologist.setName(userRequest.getName());
        dermatologist.setLastName(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        dermatologist.setAddress(address);
        dermatologist.setEmail(userRequest.getEmail());
        dermatologist.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        dermatologist.setFirstLogged(true);
        Authority authorityDermatologist = authService.findByname("ROLE_DERMATOLOGIST");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityDermatologist==null) {
            authorityRepository.save(new Authority("ROLE_DERMATOLOGIST"));
            auth.add(authService.findByname("ROLE_DERMATOLOGIST"));
        }
        else {
            auth.add(authorityDermatologist);
        }
        dermatologist.setAuthorities(auth);
        dermatologist.setEnabled(true);
        return dermatologistRepository.save(dermatologist);
	}

	@Override
	public Dermatologist getById(Long id) {
		return dermatologistRepository.findById(id).get();
	}

	@Override
	public void update(DermatologistDTO dermatologistDTO) {
		Dermatologist dermatologist = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    AddressDTO addressDTO = dermatologistDTO.getAddress();
	    City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        
	    dermatologist.setName(dermatologistDTO.getName());
	    dermatologist.setLastName(dermatologistDTO.getSurname());
	    dermatologist.setAddress(new Address(addressDTO.getCountry(), addressDTO.getStreet(), city));

	    dermatologistRepository.save(dermatologist);
	    
	}

	@Override
	public String updatePassword(DermatologistDTO dermatologistDTO) {
		Dermatologist dermatologist = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(passwordEncoder.matches(dermatologistDTO.getPassword(), dermatologist.getPassword())) {
			dermatologist.setPassword(passwordEncoder.encode(dermatologistDTO.getConfirmPassword()));
			dermatologistRepository.save(dermatologist);
			return "Succesfully";
		}else 
			return "Current password is not right! Please, try again!";
		
	}

	@Override
	public List<Dermatologist> findAll() {
		return dermatologistRepository.findAll();
	}

	public List<PatientSearchDTO> getMyPatients() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Dermatologist dermatologist = getById(person.getId());
		List<Examination> examinations = dermatologist.getExaminations();
		List<PatientSearchDTO> myPatients = new ArrayList<>();
		PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
		
		for(Examination e:examinations) {
			if(e.getPatient() != null) {
				patientSearchDTO = new PatientSearchDTO(e.getPatient().getName(),e.getPatient().getLastName(),e.getPatient().getEmail(),
						e.getStartDate().toString(), e.getStartTime().toString(), e.getId());
				myPatients.add(patientSearchDTO);
			}
		}
		
		return myPatients;
	}

	@Override
	public List<PatientSearchDTO> searchMyPatient(PatientSearchDTO patientSearchDTO) {
		List<PatientSearchDTO> myPatients = getMyPatients();
		List<PatientSearchDTO> searchResult = new ArrayList<>();
		PatientSearchDTO result  = new PatientSearchDTO();
		for(PatientSearchDTO p:myPatients) 
			if(p.getName().equals(patientSearchDTO.getName()) && p.getSurname().equals(patientSearchDTO.getSurname())) {
				result = new PatientSearchDTO(p.getName(), p.getSurname(), p.getExaminationID());
				searchResult.add(result);
			}
				
		return searchResult;
	}

	@Override
	public List<PatientSearchDTO> sortMyPatient(PatientSearchDTO sortingKey) {
		List<PatientSearchDTO> myPatients =  getMyPatients();
		
		if(sortingKey.getSortingKey().equals("name")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}else if(sortingKey.getSortingKey().equals("surname")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o1.getSurname().compareTo(o2.getSurname());
				}
			});
		}else if(sortingKey.getSortingKey().equals("startDate")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o1.getStartDate().compareTo(o2.getStartDate());
				}
			});
		}else if(sortingKey.getSortingKey().equals("startTime")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o1.getStartTime().compareTo(o2.getStartTime());
				}
			});
		}
		return myPatients;
	}

	@Override
	public List<PatientSearchDTO> sortMyPatientDescending(PatientSearchDTO sortingKey) {
		List<PatientSearchDTO> myPatients =  getMyPatients();
		
		if(sortingKey.getSortingKey().equals("name")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o2.getName().compareTo(o1.getName());
				}
			});
		}else if(sortingKey.getSortingKey().equals("surname")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o2.getSurname().compareTo(o1.getSurname());
				}
			});
		}else if(sortingKey.getSortingKey().equals("startDate")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o2.getStartDate().compareTo(o1.getStartDate());
				}
			});
		}else if(sortingKey.getSortingKey().equals("startTime")) {
			Collections.sort(myPatients, new Comparator<PatientSearchDTO>() {
				@Override
				public int compare(PatientSearchDTO o1, PatientSearchDTO o2) {
					return o2.getStartTime().compareTo(o1.getStartTime());
				}
			});
		}
		return myPatients;
	}

	
}
