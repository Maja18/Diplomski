package ISA.Team22.Domain.DTO;

public class DermatologistDTO {
	
	private String name;
	private String surname;
	private String email;
	private AddressDTO address;
	private String password;
	private String confirmPassword;
	private String newPassword;
	private String contact;
	 
	public DermatologistDTO() {
		super();
	}

	
	
	public DermatologistDTO(String name, String surname, String email, AddressDTO address, String contact) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.contact = contact;
	}


	public DermatologistDTO(String name, String surname, String email, AddressDTO address, String password,
			String confirmPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public DermatologistDTO(String name, String surname, String email, AddressDTO address) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
	}
	
	
	
	public DermatologistDTO(String password, String confirmPassword, String newPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.newPassword = newPassword;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

}
