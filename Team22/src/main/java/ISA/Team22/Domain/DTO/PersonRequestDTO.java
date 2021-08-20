package ISA.Team22.Domain.DTO;

public class PersonRequestDTO {
	
	private String email;

    private String password;
    
    private String confirmPassword;

    private String name;

    private String surname;

    private String phoneNumber;

    private AddressDTO address;

	public PersonRequestDTO() {
		super();
	}

	public PersonRequestDTO(String email, String password, String confirmPassword, String name, String surname,
			String phoneNumber, AddressDTO address) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}
     
}