package ISA.Team22.Domain.DTO;

public class DrugSpecificationDTO {
	private String contraIndications;

    private String composition;

    private Integer therapyDuration;

    private String manufacturer;

	public DrugSpecificationDTO() {
		super();
	}

	public DrugSpecificationDTO(String contraIndications, String composition, Integer therapyDuration,
			String manufacturer) {
		super();
		this.contraIndications = contraIndications;
		this.composition = composition;
		this.therapyDuration = therapyDuration;
		this.manufacturer = manufacturer;
	}

	public String getContraIndications() {
		return contraIndications;
	}

	public void setContraIndications(String contraIndications) {
		this.contraIndications = contraIndications;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public Integer getTherapyDuration() {
		return therapyDuration;
	}

	public void setTherapyDuration(Integer therapyDuration) {
		this.therapyDuration = therapyDuration;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
    
    

}