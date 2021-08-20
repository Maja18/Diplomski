package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.PharmacyAdministrator;
import ISA.Team22.Domain.Users.SystemAdministrator;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Repository.PharmacyAdministratorRepository;
import ISA.Team22.Service.IService.IPharmacyAdministratorService;

@Service
public class PharmacyAdministratorService implements IPharmacyAdministratorService {

	private final PharmacyAdministratorRepository pharmacyAdministratorRepository;
	
	private final PasswordEncoder passwordEncoder;

	private final AuthorityService authorityService;
	
	private final AuthorityRepository authorityRepository;
	
	@Autowired
    public PharmacyAdministratorService(PharmacyAdministratorRepository pharmacyAdministratorRepository,
    		PasswordEncoder passwordEncoder,AuthorityService authorityService, AuthorityRepository authorityRepository) {
		this.pharmacyAdministratorRepository = pharmacyAdministratorRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityService = authorityService;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public PharmacyAdministrator findById(Long id) {
		return pharmacyAdministratorRepository.findById(id).get();
	}

	@Override
	public PharmacyAdministrator findByEmail(String email) {
		return pharmacyAdministratorRepository.findByEmail(email);
	}

	@Override
	public List<PharmacyAdministrator> findAll() {
		return pharmacyAdministratorRepository.findAll();
	}

	@Override
	public PharmacyAdministrator save(PersonRequestDTO userRequest) {
		PharmacyAdministrator pharmacyAdministrator =  new PharmacyAdministrator();
        pharmacyAdministrator.setName(userRequest.getName());
        pharmacyAdministrator.setLastName(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        pharmacyAdministrator.setAddress(address);
        pharmacyAdministrator.setEmail(userRequest.getEmail());
        pharmacyAdministrator.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        pharmacyAdministrator.setFirstLogged(true);
        pharmacyAdministrator.setContact(userRequest.getPhoneNumber());
        Authority authoritySystemAdmin =  authorityService.findByname("ROLE_PHARMACY_ADMINISTRATOR");
        List<Authority> auth = new ArrayList<Authority>();
        if(authoritySystemAdmin==null) {
            authorityRepository.save(new Authority("ROLE_PHARMACY_ADMINISTRATOR"));
            auth.add((Authority) authorityService.findByname("ROLE_PHARMACY_ADMINISTRATOR"));
        }
        else {
            auth.add(authoritySystemAdmin);
        }
        pharmacyAdministrator.setAuthorities(auth);
        pharmacyAdministrator.setEnabled(true);
        return pharmacyAdministratorRepository.save(pharmacyAdministrator);
	}
}
