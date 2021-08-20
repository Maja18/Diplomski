package ISA.Team22.Domain.DTO;

public class AddressDTO {

	private String town;

    private String street;

    private String number;
    
    private String country;

	public AddressDTO() {
		super();
	}

	public AddressDTO(String town, String street, String number, String country) {
		super();
		this.town = town;
		this.street = street;
		this.number = number;
		this.country = country;
	}
	
	

	public AddressDTO(String street) {
		super();
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
}
