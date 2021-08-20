package ISA.Team22.Domain.DrugManipulation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ISA.Team22.Domain.PharmacyWorkflow.Period;


@Entity
public class DrugInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "price", nullable = false)
	private Double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
	private Period period;
	
	@Column(name = "drugAmount", nullable = false)
	private Integer drugAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
	private Drug drug;
	
	public DrugInfo() {
		super();
	}

	public DrugInfo(Long id, Double price, Period period, Integer drugAmount, Drug drug) {
		super();
		this.price = price;
		this.period = period;
		this.drugAmount = drugAmount;
		this.drug = drug;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Integer getDrugAmount() {
		return drugAmount;
	}

	public void setDrugAmount(Integer drugAmount) {
		this.drugAmount = drugAmount;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	
	
}
