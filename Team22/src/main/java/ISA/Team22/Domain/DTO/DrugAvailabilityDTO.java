package ISA.Team22.Domain.DTO;

public class DrugAvailabilityDTO {
	private Long drugId;
	private String drugCode;
	private Long pharmacyId;
	private String pharmacyName;
	private Double price;
	private Long examinationID;
	private Long patientId;
	
	public DrugAvailabilityDTO() {
		super();
	}
    

	public DrugAvailabilityDTO(Long drugId, String drugCode, Long pharmacyId, String pharmacyName, Double price,
			Long examinationID, Long patientId) {
		super();
		this.drugId = drugId;
		this.drugCode = drugCode;
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.examinationID = examinationID;
		this.patientId = patientId;
	}
	
	public DrugAvailabilityDTO(Long drugId, String drugCode, Long pharmacyId, String pharmacyName, Double price) {
		super();
		this.drugId = drugId;
		this.drugCode = drugCode;
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.price = price;
	}


	public String getDrugCode() {
		return drugCode;
	}


	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public Long getDrugId() {
		return drugId;
	}



	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}



	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public Long getExamiantionID() {
		return examinationID;
	}


	public void setExamiantionID(Long examiantionID) {
		this.examinationID = examiantionID;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	

}
