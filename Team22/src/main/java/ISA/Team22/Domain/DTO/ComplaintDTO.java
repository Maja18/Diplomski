package ISA.Team22.Domain.DTO;

public class ComplaintDTO {
	
	private Long id;
	private String description;
	private String answer;
	private Long pharmacistId;
	private Boolean isAnswered;
	private Long patientId;
	private Long pharmacyId;
	private Long dermatologyId;
	
	public ComplaintDTO() {
		super();
	}

	
	public ComplaintDTO(Long id, String description, String answer, Long pharmacistId, Boolean isAnswered,
			Long patientId, Long pharmacyId, Long dermatologyId) {
		super();
		this.id = id;
		this.description = description;
		this.answer = answer;
		this.pharmacistId = pharmacistId;
		this.isAnswered = isAnswered;
		this.patientId = patientId;
		this.pharmacyId = pharmacyId;
		this.dermatologyId = dermatologyId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public Boolean getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getPharmacistId() {
		return pharmacistId;
	}


	public void setPharmacistId(Long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}


	public Long getDermatologyId() {
		return dermatologyId;
	}


	public void setDermatologyId(Long dermatologyId) {
		this.dermatologyId = dermatologyId;
	}
	
	
}
