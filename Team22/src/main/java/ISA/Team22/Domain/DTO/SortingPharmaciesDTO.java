package ISA.Team22.Domain.DTO;

import java.util.List;

public class SortingPharmaciesDTO {
	private String sortingKey;
	private List<PharmacyDrugAvailabilityDTO> pharmaciesList;
	public SortingPharmaciesDTO() {
		super();
	}
	public SortingPharmaciesDTO(String sortingKey, List<PharmacyDrugAvailabilityDTO> pharmaciesList) {
		super();
		this.sortingKey = sortingKey;
		this.pharmaciesList = pharmaciesList;
	}
	public String getSortingKey() {
		return sortingKey;
	}
	public void setSortingKey(String sortingKey) {
		this.sortingKey = sortingKey;
	}
	public List<PharmacyDrugAvailabilityDTO> getPharmaciesList() {
		return pharmaciesList;
	}
	public void setPharmaciesList(List<PharmacyDrugAvailabilityDTO> pharmaciesList) {
		this.pharmaciesList = pharmaciesList;
	}
	
	

}
