package ISA.Team22.Domain.Pharmacy;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Domain.Users.PharmacyAdministrator;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private PurchaseOrderStatus purchaseOrderStatus;
	
	
	@Column(name = "dueDate",  nullable = false)
	private LocalDate dueDate;
	
	@JsonBackReference(value = "pharmacyAdministrator-order")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PharmacyAdministrator pharmacyAdministrator;
	
	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offer;
	
	@ManyToMany(targetEntity = PurchaseOrderDrug.class,  cascade = CascadeType.ALL)
	private List<PurchaseOrderDrug> purchaseOrderDrugs;
	
	public PurchaseOrder() {
		super();
	}

	public PurchaseOrder(Long id, PurchaseOrderStatus purchaseOrderStatus, LocalDate dueDate,
			PharmacyAdministrator pharmacyAdministrator, List<Offer> offer, List<PurchaseOrderDrug> purchaseOrderDrugs) {
		super();
		this.id = id;
		this.purchaseOrderStatus = purchaseOrderStatus;
		this.dueDate = dueDate;
		this.pharmacyAdministrator = pharmacyAdministrator;
		this.offer = offer;
		this.purchaseOrderDrugs = purchaseOrderDrugs;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public PurchaseOrderStatus getPurchaseOrderStatus() {
		return purchaseOrderStatus;
	}

	public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
		this.purchaseOrderStatus = purchaseOrderStatus;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public PharmacyAdministrator getPharmacyAdministrator() {
		return pharmacyAdministrator;
	}

	public void setPharmacyAdministrator(PharmacyAdministrator pharmacyAdministrator) {
		this.pharmacyAdministrator = pharmacyAdministrator;
	}

	public List<Offer> getOffer() {
		return offer;
	}

	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}

	public List<PurchaseOrderDrug> getPurchaseOrderDrugs() {
		return purchaseOrderDrugs;
	}

	public void setPurchaseOrderDrugs(List<PurchaseOrderDrug> purchaseOrderDrugs) {
		this.purchaseOrderDrugs = purchaseOrderDrugs;
	}
	   
}
