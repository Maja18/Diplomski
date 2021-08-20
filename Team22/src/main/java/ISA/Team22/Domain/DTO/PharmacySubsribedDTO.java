package ISA.Team22.Domain.DTO;

public class PharmacySubsribedDTO {
   private Long pharmacyId;
   private String pharmacyName;
   
   public PharmacySubsribedDTO() {
	super();
   }

public PharmacySubsribedDTO(Long pharmacyId, String pharmacyName) {
	super();
	this.pharmacyId = pharmacyId;
	this.pharmacyName = pharmacyName;
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
   
   
   
}
