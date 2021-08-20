package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.PatientFrontDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Repository.PatientRepository;
import ISA.Team22.Service.IService.IPatientService;

@Service
public class PatientService implements IPatientService {

	private final PatientRepository patientRepository;
	
	@Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient findByEmail(String email) {
		return patientRepository.findByEmail(email);
	}

	@Override
	public Patient save(PersonRequestDTO userRequest) {
		Patient patient =  new Patient();
        patient.setName(userRequest.getName());
        patient.setLastName(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        patient.setAddress(address);
        patient.setEmail(userRequest.getEmail());
        patient.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        patient.setFirstLogged(true);
        patient.setContact(userRequest.getPhoneNumber());
        Authority authorityDermatologist = authService.findByname("ROLE_PATIENT");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityDermatologist==null) {
            authorityRepository.save(new Authority("ROLE_PATIENT"));
            auth.add(authService.findByname("ROLE_PATIENT"));
        }
        else {
            auth.add(authorityDermatologist);
        }
        patient.setAuthorities(auth);
        patient.setEnabled(false);
        return patientRepository.save(patient);
	}

	@Override
	public Patient findById(Long id) {
		return patientRepository.findById(id).get();
	}
	
	public List<PatientFrontDTO> getAll() {
		List<Patient> patients = patientRepository.findAll();
		List<PatientFrontDTO> pdto = new ArrayList<>();
		for(Patient p:patients) {
			pdto.add(new PatientFrontDTO(p.getId(),p.getName(), p.getLastName(), p.getEmail()));
		}
		return pdto;
	}
	
	@Override
	public List<Examination> getAllMyExaminations(Long id) {

		Patient patient = patientRepository.findById(id).get();
		return patient.getExaminations();
	}
	
	@Override
	public List<Counseling> getAllMyCounselings(Long id) {
		Patient patient = patientRepository.findById(id).get();
		return patient.getCounseling();
	}
	
	public void saveChanges(Patient patient) {
		patientRepository.save(patient);
	}
	
}
