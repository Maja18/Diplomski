package ISA.Team22.Domain.DTO;

public class PrescriptionDTO {
	
	private Double durationOfTherapy;
	
	private Integer amountOfDrug;
	
	private Long pharmacyId;
	
	private Long patientId;
	
	private Long drugId;
	
	public PrescriptionDTO() {
		super();
	}

	public PrescriptionDTO(Double durationOfTherapy, Integer amountOfDrug, Long pharmacyId, Long patientId,
			Long drugId) {
		super();
		this.durationOfTherapy = durationOfTherapy;
		this.amountOfDrug = amountOfDrug;
		this.pharmacyId = pharmacyId;
		this.patientId = patientId;
		this.drugId = drugId;
	}

	public Double getDurationOfTherapy() {
		return durationOfTherapy;
	}

	public void setDurationOfTherapy(Double durationOfTherapy) {
		this.durationOfTherapy = durationOfTherapy;
	}

	public Integer getAmountOfDrug() {
		return amountOfDrug;
	}

	public void setAmountOfDrug(Integer amountOfDrug) {
		this.amountOfDrug = amountOfDrug;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	
	
}
