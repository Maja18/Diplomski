package ISA.Team22.Domain.DTO;

public class PharmacyDTO {

	private String name;
	
	private AddressDTO addressDTO;
	
	private String description;

	public PharmacyDTO() {
		super();
	}

	public PharmacyDTO(String name, AddressDTO addressDTO, String description) {
		super();
		this.name = name;
		this.addressDTO = addressDTO;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
