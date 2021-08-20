package ISA.Team22.Domain.DTO;


import ISA.Team22.Domain.DrugManipulation.DrugForm;
import ISA.Team22.Domain.DrugManipulation.DrugType;
import ISA.Team22.Domain.Examination.IssuanceRegime;

public class DrugSearchDTO {
	
	private Long id;
	private String name;
    private String code;
    private DrugType type;
    private DrugForm drugForm;
    private IssuanceRegime issuance;    
    private Integer numberOfGrades;
    private DrugSpecificationDTO specification;
    private String sortingKey;
    private Boolean isAvailable;
    
	public DrugSearchDTO() {
		super();
	}
	
	public DrugSearchDTO(Long id, String name, String code, DrugType type, DrugForm drugForm, IssuanceRegime issuance,
			Integer numberOfGrades, DrugSpecificationDTO specification) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.type = type;
		this.drugForm = drugForm;
		this.issuance = issuance;
		this.numberOfGrades = numberOfGrades;
		this.specification = specification;
	}
	
	public DrugSearchDTO(Long id, String name,  DrugType type, DrugForm drugForm) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.drugForm = drugForm;
	}

	public DrugSearchDTO(Long id, String name, String code, DrugType type, DrugForm drugForm, IssuanceRegime issuance,
			Integer numberOfGrades, DrugSpecificationDTO specification, String sortingKey) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.type = type;
		this.drugForm = drugForm;
		this.issuance = issuance;
		this.numberOfGrades = numberOfGrades;
		this.specification = specification;
		this.sortingKey = sortingKey;
	}
	
	public DrugSearchDTO(Long id, String name,  DrugType type, DrugForm drugForm,Boolean isAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.drugForm = drugForm;
		this.isAvailable = isAvailable;
	}
	
	public DrugSearchDTO(Long id) {
		super();
		this.id = id;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public DrugType getType() {
		return type;
	}
	public void setType(DrugType type) {
		this.type = type;
	}
	
	public Integer getNumberOfGrades() {
		return numberOfGrades;
	}

	public void setNumberOfGrades(Integer numberOfGrades) {
		this.numberOfGrades = numberOfGrades;
	}

	public DrugForm getDrugForm() {
		return drugForm;
	}
	public void setDrugForm(DrugForm drugForm) {
		this.drugForm = drugForm;
	}
	public IssuanceRegime getIssuance() {
		return issuance;
	}
	public void setIssuance(IssuanceRegime issuance) {
		this.issuance = issuance;
	}
	public DrugSpecificationDTO getSpecification() {
		return specification;
	}
	public void setSpecification(DrugSpecificationDTO specification) {
		this.specification = specification;
	}

	public String getSortingKey() {
		return sortingKey;
	}

	public void setSortingKey(String sortingKey) {
		this.sortingKey = sortingKey;
	}
	
	
}
