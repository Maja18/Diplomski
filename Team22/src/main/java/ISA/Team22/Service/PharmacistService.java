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
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.DTO.PharmacistDTO;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Repository.PharmacistRepository;
import ISA.Team22.Service.IService.IPharmacistService;

@Service
public class PharmacistService implements IPharmacistService {


	private final PharmacistRepository pharmacistRepository;
    private final AuthorityService authService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
	
  
	@Autowired
	public PharmacistService(PharmacistRepository pharmacistRepository, AuthorityService authService, AuthorityRepository authorityRepository,
			PasswordEncoder passwordEncoder) {
		
		this.pharmacistRepository = pharmacistRepository;
		this.authService = authService;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public Pharmacist getById(Long id) {
		return pharmacistRepository.findById(id).get();
	}

	@Override
	public Pharmacist findByEmail(String email) {
		return pharmacistRepository.findByEmail(email);
	}

	@Override
	public Pharmacist save(PharmacistDTO userRequest) {
		Pharmacist pharmacist =  new Pharmacist();
		pharmacist.setName(userRequest.getName());
		pharmacist.setLastName(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        pharmacist.setAddress(address);
        pharmacist.setEmail(userRequest.getEmail());
        pharmacist.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        pharmacist.setFirstLogged(true);
        Authority authorityDermatologist = authService.findByname("ROLE_PHARMACIST");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityDermatologist==null) {
            authorityRepository.save(new Authority("ROLE_PHARMACIST"));
            auth.add(authService.findByname("ROLE_PHARMACIST"));
        }
        else {
            auth.add(authorityDermatologist);
        }
        pharmacist.setAuthorities(auth);
        pharmacist.setEnabled(true);
        return pharmacistRepository.save(pharmacist);
	}

	@Override
	public void update(PharmacistDTO pharmacistDTO) {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    AddressDTO addressDTO = pharmacistDTO.getAddress();
	    City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        
        pharmacist.setName(pharmacistDTO.getName());
        pharmacist.setLastName(pharmacistDTO.getSurname());
        pharmacist.setAddress(new Address(addressDTO.getCountry(), addressDTO.getStreet(), city));

        pharmacistRepository.save(pharmacist);
		
	}

	@Override
	public String updatePassword(PharmacistDTO pharmacistDTO) {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(passwordEncoder.matches(pharmacistDTO.getPassword(), pharmacist.getPassword())) {
			pharmacist.setPassword(passwordEncoder.encode(pharmacistDTO.getConfirmPassword()));
			pharmacistRepository.save(pharmacist);
		    return "Succesfully";
		}else
			return "Current password is not right! Please, try again!";
		
	}

	@Override
	public List<Pharmacist> findAll() {
		return pharmacistRepository.findAll();
	}

	@Override
	public List<PatientSearchDTO> getMyPatients() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Pharmacist pharmacist = getById(person.getId());
		List<Counseling> counselings = pharmacist.getCounselings();
		List<PatientSearchDTO> myPatients = new ArrayList<>();
		PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
		
		for(Counseling e:counselings) {
			patientSearchDTO = new PatientSearchDTO(e.getPatient().getName(),e.getPatient().getLastName(),e.getPatient().getEmail(),
					e.getStartDate().toString(), e.getStartTime().toString(), e.getId());
			myPatients.add(patientSearchDTO);
		}
		
		return myPatients;
	}

	@Override
	public List<PatientSearchDTO> searchMyPatient(PatientSearchDTO patientSearchDTO) {
		List<PatientSearchDTO> myPatients = getMyPatients();
		List<PatientSearchDTO> searchResult = new ArrayList<>(); 
		for(PatientSearchDTO p:myPatients) 
			if(p.getName().equals(patientSearchDTO.getName()) && p.getSurname().equals(patientSearchDTO.getSurname())) 
				searchResult.add(new PatientSearchDTO(p.getName(), p.getSurname(), p.getExaminationID()));
				
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
