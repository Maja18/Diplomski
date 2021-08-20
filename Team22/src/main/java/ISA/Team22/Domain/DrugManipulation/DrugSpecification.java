package ISA.Team22.Domain.DrugManipulation;

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
import javax.persistence.OneToMany;

@Entity
public class DrugSpecification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "contraindications", nullable = false)
	private String contraindications;
	
	@Column(name = "therapy")
	private String therapy;
	
	@Column(name = "therapyDuration")
	private Integer therapyDuration;
	
	@Column(name = "compositions", nullable = false)
	private String compositions;
	
	 @ManyToMany
	 @JoinTable(name = "medications_alternatives", 
	 joinColumns = @JoinColumn(name = "drug_specification_id", referencedColumnName = "id"), 
	 inverseJoinColumns = @JoinColumn(name = "drug_alternative_id", referencedColumnName = "id"))
	//@OneToMany(mappedBy = "drugSpecification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Drug> alternativeDrugCodes;
	
	public DrugSpecification() {
		super();
	}
	public DrugSpecification(Long id, String contraindications, String therapy, String compositions,
			List<Drug> substituteDrugCodes, Integer therapyDuration) {
		super();
		this.contraindications = contraindications;
		this.therapy = therapy;
		this.therapyDuration = therapyDuration;
		this.compositions = compositions;
		this.alternativeDrugCodes = substituteDrugCodes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContraindications() {
		return contraindications;
	}
	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}
	public String getTherapy() {
		return therapy;
	}
	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}
	
	public Integer getTherapyDuration() {
		return therapyDuration;
	}
	public void setTherapyDuration(Integer therapyDuration) {
		this.therapyDuration = therapyDuration;
	}
	public String getCompositions() {
		return compositions;
	}
	public void setCompositions(String compositions) {
		this.compositions = compositions;
	}
	public List<Drug> getSubstituteDrugCodes() {
		return alternativeDrugCodes;
	}
	public void setSubstituteDrugCodes(List<Drug> substituteDrugCodes) {
		this.alternativeDrugCodes = substituteDrugCodes;
	}
	public List<Drug> getAlternativeDrugCodes() {
		return alternativeDrugCodes;
	}
	public void setAlternativeDrugCodes(List<Drug> alternativeDrugCodes) {
		this.alternativeDrugCodes = alternativeDrugCodes;
	}
	
}
