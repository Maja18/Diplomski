package ISA.Team22.Domain.PharmacyWorkflow;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ISA.Team22.Domain.DrugManipulation.Drug;

@Entity
public class PurchaseOrderDrug {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
	private Drug drug;
	
	public PurchaseOrderDrug() {
		super();
	}
	public PurchaseOrderDrug(Long id, Integer amount, Drug drug) {
		super();
		this.id = id;
		this.amount = amount;
		this.drug = drug;
	}
	
	public PurchaseOrderDrug(Integer amount, Drug drug) {
		super();
		this.amount = amount;
		this.drug = drug;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	 
}
