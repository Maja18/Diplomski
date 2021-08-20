package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.DTO.SupplierDTO;
import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Repository.SupplierRepository;
import ISA.Team22.Service.IService.ISupplierService;

@Service
public class SupplierService implements ISupplierService  {

	private final SupplierRepository supplierRepository;
	
	@Autowired
    private AuthorityService authService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SupplierService(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Supplier findByEmail(String email) {
		return supplierRepository.findByEmail(email);
	}

	@Override
	public Supplier save(PersonRequestDTO userRequest) {
		Supplier supplier =  new Supplier();
        supplier.setName(userRequest.getName());
        supplier.setLastName(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        supplier.setAddress(address);
        supplier.setEmail(userRequest.getEmail());
        supplier.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        supplier.setFirstLogged(true);
        supplier.setContact(userRequest.getPhoneNumber());
        Authority authorityDermatologist = authService.findByname("ROLE_SUPPLIER");
        List<Authority> auth = new ArrayList<Authority>();
        if(authorityDermatologist==null) {
            authorityRepository.save(new Authority("ROLE_SUPPLIER"));
            auth.add(authService.findByname("ROLE_SUPPLIER"));
        }
        else {
            auth.add(authorityDermatologist);
        }
        supplier.setAuthorities(auth);
        supplier.setEnabled(true);
        return supplierRepository.save(supplier);
	}

	@Override
	public Supplier findById(Long id) {
		return supplierRepository.findById(id).get();
	}

	@Override
	public void update(SupplierDTO supplierDTO) {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    AddressDTO addressDTO = supplierDTO.getAddress();
	    City city = new City();
        city.setName(addressDTO.getTown());
        Country country = new Country();
        country.setName(addressDTO.getCountry());
        city.setCountry(country);
        Address address = new Address(addressDTO.getStreet(),addressDTO.getNumber(),city);
        supplier.setAddress(address);
        supplier.setName(supplierDTO.getName());
        supplier.setLastName(supplierDTO.getSurname());
        
	    supplierRepository.save(supplier);
		
	}

	@Override
	public String updatePassword(SupplierDTO supplierDTO) {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(passwordEncoder.matches(supplierDTO.getPassword(), supplier.getPassword())) {
			supplier.setPassword(passwordEncoder.encode(supplierDTO.getConfirmPassword()));
			supplierRepository.save(supplier);
			return "Succesfully";
		}else {
			throw new IllegalArgumentException("Please enter valid current password!");
		}
	}
}
