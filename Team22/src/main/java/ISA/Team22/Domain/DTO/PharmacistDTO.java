package ISA.Team22.Domain.DTO;

public class PharmacistDTO {
	
	private String name;
	private String surname;
	private String email;
	private AddressDTO address;	 
	private String password;
	private String confirmPassword;
	private String contact;
	 
	public PharmacistDTO() {
		super();
	}

	
	public PharmacistDTO(String name, String surname, String email, AddressDTO address, String password,
			String confirmPassword, String contact) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contact = contact;
	}
	public PharmacistDTO(String name, String surname, String email, AddressDTO address) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
	}

	public PharmacistDTO(String name, String surname, String email, AddressDTO address, String contact) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.contact = contact;
	}
	
	
	public PharmacistDTO(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
