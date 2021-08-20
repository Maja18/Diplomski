package ISA.Team22.Domain.DTO;

public class SupplierDTO {
	private String name;

	 private String surname;

	 private String email;

	 private AddressDTO address;
	 
	 private String password;
	 
	 private String confirmPassword;

	public SupplierDTO() {
		super();
	}

	public SupplierDTO(String name, String surname, String email, AddressDTO address, String password,
			String confirmPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	
	public SupplierDTO(String name, String surname, String email,AddressDTO address, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	 

}
