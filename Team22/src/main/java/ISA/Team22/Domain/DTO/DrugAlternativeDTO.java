package ISA.Team22.Domain.DTO;

public class DrugAlternativeDTO {

	private Long drugId;
	private Long patientId;

	public DrugAlternativeDTO() {
		super();
	}
	

	public DrugAlternativeDTO(Long drugId, Long patientId) {
		super();
		this.drugId = drugId;
		this.patientId = patientId;
	}


	public Long getDrugId() {
		return drugId;
	}


	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}



}
