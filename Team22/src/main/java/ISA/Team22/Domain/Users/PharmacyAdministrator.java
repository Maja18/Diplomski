package ISA.Team22.Domain.Users;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.PharmacyWorkflow.Notification;

@Entity
@DiscriminatorValue("PharmacyAdministrator")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PharmacyAdministrator extends Person{
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacy pharmacy;
	
	@JsonManagedReference(value="pharmacyAdministrator-order")
	@OneToMany(mappedBy = "pharmacyAdministrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PurchaseOrder> purchaseOrders;
	
	@ManyToMany(targetEntity = AbsenceRequestDermatologist.class,  cascade = CascadeType.ALL)
	private List<AbsenceRequestDermatologist> absenceRequestDermatologists;
	
	@ManyToMany(targetEntity = AbsenceRequestPharmacist.class,  cascade = CascadeType.ALL)
	private List<AbsenceRequestPharmacist> absenceRequestPharmacists;
	
	@ManyToMany(targetEntity = Notification.class,  cascade = CascadeType.ALL)
	private List<Notification> notifications;
	
	public PharmacyAdministrator() {
		super();
	}


	public PharmacyAdministrator(Long id, String email, String password, String name, String lastName, String contact,
			Address address, Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities,
			Boolean firstLogged, Pharmacy pharmacy, List<PurchaseOrder> purchaseOrders,
			List<AbsenceRequestDermatologist> absenceRequestDermatologists, List<AbsenceRequestPharmacist> absenceRequestPharmacists,
			List<Notification> notifications) {
		super(id, email, password, name, lastName, contact, address, lastPasswordResetDate, enabled, authorities, firstLogged);
		this.pharmacy = pharmacy;
		this.purchaseOrders = purchaseOrders;
		this.absenceRequestDermatologists = absenceRequestDermatologists;
		this.absenceRequestPharmacists = absenceRequestPharmacists;
		this.notifications = notifications;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}


	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}


	public List<AbsenceRequestDermatologist> getAbsenceRequestDermatologists() {
		return absenceRequestDermatologists;
	}


	public void setAbsenceRequestDermatologists(List<AbsenceRequestDermatologist> absenceRequestDermatologists) {
		this.absenceRequestDermatologists = absenceRequestDermatologists;
	}


	public List<AbsenceRequestPharmacist> getAbsenceRequestPharmacists() {
		return absenceRequestPharmacists;
	}


	public void setAbsenceRequestPharmacists(List<AbsenceRequestPharmacist> absenceRequestPharmacists) {
		this.absenceRequestPharmacists = absenceRequestPharmacists;
	}


	public List<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	
}
