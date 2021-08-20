package ISA.Team22.Domain.DTO;

public class PharmacyPromotionDTO {
     private Long pharmacyId;

	public PharmacyPromotionDTO() {
		super();
	}

	public PharmacyPromotionDTO(Long pharmacyId) {
		super();
		this.pharmacyId = pharmacyId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
     
     
}
