package ISA.Team22.Domain.PharmacyWorkflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.Pharmacy.Pharmacy;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
	private Drug drug;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false)
	private Pharmacy pharmacy;
	
	public Notification() {
		super();
	}

	public Notification(Drug drug, Pharmacy pharmacy) {
		super();
		this.drug = drug;
		this.pharmacy = pharmacy;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
    
	
	
    
}
