package ISA.Team22.Domain.PharmacyWorkflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoyaltyProgram {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "examinationPoints",  nullable = false)
	private Integer examinationPoints;
	
	@Column(name = "consultingPoints",  nullable = false)
	private Integer consultingPoints;
	
	@Column(name = "regularDiscount", nullable = true)
	private String regularDiscount;


	@Column(name = "silverDiscount", nullable = true)
	private String silverDiscount;


	@Column(name = "goldDiscount", nullable = true)
	private String goldDiscount;
	
	@Column(name = "minimumSilverPoints", nullable = true)
	private Integer minimumSilverPoints;
	
	@Column(name = "minimumGoldPoints", nullable = true)
	private Integer minimumGoldPoints;
	
	public LoyaltyProgram() {
		super();
	}
    

	public LoyaltyProgram(Long id, Integer examinationPoints, Integer consultingPoints, String regularDiscount,
			String silverDiscount, String goldDiscount, Integer minimumSilverPoints, Integer minimumGoldPoints) {
		super();
		this.id = id;
		this.examinationPoints = examinationPoints;
		this.consultingPoints = consultingPoints;
		this.regularDiscount = regularDiscount;
		this.silverDiscount = silverDiscount;
		this.goldDiscount = goldDiscount;
		this.minimumSilverPoints = minimumSilverPoints;
		this.minimumGoldPoints = minimumGoldPoints;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getExaminationPoints() {
		return examinationPoints;
	}

	public void setExaminationPoints(Integer examinationPoints) {
		this.examinationPoints = examinationPoints;
	}

	public Integer getConsultingPoints() {
		return consultingPoints;
	}

	public void setConsultingPoints(Integer consultingPoints) {
		this.consultingPoints = consultingPoints;
	}

	public String getRegularDiscount() {
		return regularDiscount;
	}

	public void setRegularDiscount(String regularDiscount) {
		this.regularDiscount = regularDiscount;
	}

	public String getSilverDiscount() {
		return silverDiscount;
	}

	public void setSilverDiscount(String silverDiscount) {
		this.silverDiscount = silverDiscount;
	}

	public String getGoldDiscount() {
		return goldDiscount;
	}

	public void setGoldDiscount(String goldDiscount) {
		this.goldDiscount = goldDiscount;
	}

	public Integer getMinimumSilverPoints() {
		return minimumSilverPoints;
	}

	public void setMinimumSilverPoints(Integer minimumSilverPoints) {
		this.minimumSilverPoints = minimumSilverPoints;
	}

	public Integer getMinimumGoldPoints() {
		return minimumGoldPoints;
	}
	
	public void setMinimumGoldPoints(Integer minimumGoldPoints) {
		this.minimumGoldPoints = minimumGoldPoints;
	}
	
}
