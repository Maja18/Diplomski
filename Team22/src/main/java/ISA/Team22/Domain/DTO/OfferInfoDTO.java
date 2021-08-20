package ISA.Team22.Domain.DTO;

import java.time.LocalDate;
import java.util.List;

import ISA.Team22.Domain.Pharmacy.OfferStatus;


public class OfferInfoDTO {
	private Long offerId;
    private Long purchaseOrderId;
    private LocalDate deliveryTime;
    private Double totalPrice;
    private LocalDate date;
    private List<DrugOrderDTO> drugs;
    private String pharmacyName;
    private boolean isOfferGiven;
    
    private OfferStatus status;
    
	public OfferInfoDTO() {
		super();
	}

	public OfferInfoDTO(Long offerId, Long purchaseOrderId, LocalDate deliveryTime, Double totalPrice,
			LocalDate date, List<DrugOrderDTO> drugs, String pharmacyName, boolean isOfferGiven,OfferStatus status) {
		super();
		this.offerId = offerId;
		this.purchaseOrderId = purchaseOrderId;
		this.deliveryTime = deliveryTime;
		this.totalPrice = totalPrice;
		this.date = date;
		this.drugs = drugs;
		this.pharmacyName = pharmacyName;
		this.isOfferGiven = isOfferGiven;
		this.status = status;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public LocalDate getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDate deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<DrugOrderDTO> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<DrugOrderDTO> drugs) {
		this.drugs = drugs;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public boolean isOfferGiven() {
		return isOfferGiven;
	}

	public void isOfferGiven(boolean changable) {
		this.isOfferGiven = changable;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}
    
    

}
