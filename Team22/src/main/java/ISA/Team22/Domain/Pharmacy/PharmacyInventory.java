package ISA.Team22.Domain.Pharmacy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;

@Entity
public class PharmacyInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "pharmacyInventory")
	private Pharmacy pharmacy;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "pharmacy_inventory_drug_infos",
    joinColumns = @JoinColumn(name = "pharmacy_inventory_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "drug_infos_id", referencedColumnName = "id"))
	private List<DrugInfo> drugInfos;
	
	public PharmacyInventory() {
		super();
	}

	public PharmacyInventory(Long id, Pharmacy pharmacy, List<DrugInfo> drugInfos) {
		super();
		this.id = id;
		this.pharmacy = pharmacy;
		this.drugInfos = drugInfos;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<DrugInfo> getDrugInfos() {
		return drugInfos;
	}

	public void setDrugInfos(List<DrugInfo> drugInfos) {
		this.drugInfos = drugInfos;
	}


}
