package ISA.Team22.Domain.DTO;

public class PharmacyBasicDTO {

	private Long id;

    private String name;
    
	public PharmacyBasicDTO() {
		super();
	}

	public PharmacyBasicDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
