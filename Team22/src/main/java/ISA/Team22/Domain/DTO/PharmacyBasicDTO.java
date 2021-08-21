package ISA.Team22.Domain.DTO;

public class PharmacyBasicDTO {

	private Long id;

    private String name;
    
    private Integer grade;
    
    private String city;
    
    private String street;
    
    private String number;
    
	public PharmacyBasicDTO() {
		super();
	}

	
	public PharmacyBasicDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	

	public PharmacyBasicDTO(Long id, String name, Integer grade, String city, String street, String number) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.city = city;
		this.street = street;
		this.number = number;
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


	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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
	
	

	
}
