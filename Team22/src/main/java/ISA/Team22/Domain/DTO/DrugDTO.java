package ISA.Team22.Domain.DTO;

import java.util.List;

import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugForm;
import ISA.Team22.Domain.DrugManipulation.DrugType;
import ISA.Team22.Domain.Examination.IssuanceRegime;

public class DrugDTO {
	
	private Long drugId;
	
	private String name;

    private String code;
    
    private DrugType type;
    
    private DrugForm drugForm;
    
    private IssuanceRegime issuance;
    
    private List<Drug> alternativeDrugCodes;
    
    private Integer points;
	
    private DrugSpecificationDTO specification;

    private Boolean drugAvailibility;
    
	public DrugDTO() {
		super();
	}

	public DrugDTO(String name, String code, DrugType type, DrugForm drugForm, IssuanceRegime issuance,
			List<Drug> alternativeDrugCodes, Integer points, DrugSpecificationDTO specification) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
		this.drugForm = drugForm;
		this.issuance = issuance;
		this.alternativeDrugCodes = alternativeDrugCodes;
		this.points = points;
		this.specification = specification;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public DrugDTO(String name, String code, DrugType type, DrugForm drugForm, IssuanceRegime issuance,
			List<Drug> alternativeDrugCodes, DrugSpecificationDTO specification, Boolean drugAvailibility) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
		this.drugForm = drugForm;
		this.issuance = issuance;
		this.alternativeDrugCodes = alternativeDrugCodes;
		this.specification = specification;
		this.drugAvailibility = drugAvailibility;
	}
	
	public DrugDTO(Long drugId, String name, DrugType type, DrugForm drugForm, Boolean drugAvailibility) {
		super();
		this.drugId = drugId;
		this.name = name;
		this.type = type;
		this.drugForm = drugForm;
		this.drugAvailibility = drugAvailibility;
	}

	public DrugDTO(Long drugId, String name) {
		super();
		this.drugId = drugId;
		this.name = name;
	}
	
	public DrugDTO(Boolean drugAvailibility) {
		super();
		this.drugAvailibility = drugAvailibility;
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

	public DrugForm getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(DrugForm drugForm) {
		this.drugForm = drugForm;
	}

	public DrugSpecificationDTO getSpecification() {
		return specification;
	}

	public void setSpecification(DrugSpecificationDTO specification) {
		this.specification = specification;
	}



	public IssuanceRegime getIssuance() {
		return issuance;
	}



	public void setIssuance(IssuanceRegime issuance) {
		this.issuance = issuance;
	}


	public List<Drug> getAlternativeDrugCodes() {
		return alternativeDrugCodes;
	}


	public void setAlternativeDrugCodes(List<Drug> alternativeDrugCodes) {
		this.alternativeDrugCodes = alternativeDrugCodes;
	}
	
	

	

}
