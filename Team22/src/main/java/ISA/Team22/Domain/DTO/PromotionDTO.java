package ISA.Team22.Domain.DTO;

public class PromotionDTO {
	private Long id;
	private String description;
	private Long pharmacyId;
	private PromotionPeriodDTO period;
	
	public PromotionDTO() {
		super();
	}

	public PromotionDTO(Long id, String description, Long pharmacyId, PromotionPeriodDTO period) {
		super();
		this.id = id;
		this.description = description;
		this.pharmacyId = pharmacyId;
		this.period = period;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public PromotionPeriodDTO getPeriod() {
		return period;
	}

	public void setPeriod(PromotionPeriodDTO period) {
		this.period = period;
	}
	
	}
