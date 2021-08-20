package ISA.Team22.Domain.DTO;

import java.util.List;

public class PharmacyDrugAvailabilityDTO {
	 private Long pharmacyId;
	 private double sumPrice;
	 private Integer mark;
	 private AddressDTO address;
	 private String pharmacyName;
	 private String sortingKey;
	 
	 
	public PharmacyDrugAvailabilityDTO() {
		super();
	}
	public PharmacyDrugAvailabilityDTO(Long pharmacyId, double sumPrice, Integer mark, AddressDTO address,
			String pharmacyName) {
		super();
		this.pharmacyId = pharmacyId;
		this.sumPrice = sumPrice;
		this.mark = mark;
		this.address = address;
		this.pharmacyName = pharmacyName;
	}

	public PharmacyDrugAvailabilityDTO(Long pharmacyId, double sumPrice, Integer mark, AddressDTO address,
			String pharmacyName, String sortingKey) {
		super();
		this.pharmacyId = pharmacyId;
		this.sumPrice = sumPrice;
		this.mark = mark;
		this.address = address;
		this.pharmacyName = pharmacyName;
		this.sortingKey = sortingKey;
	}
	
	
	
	public Long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getSortingKey() {
		return sortingKey;
	}
	public void setSortingKey(String sortingKey) {
		this.sortingKey = sortingKey;
	}
	
	 
	    
	    
}
