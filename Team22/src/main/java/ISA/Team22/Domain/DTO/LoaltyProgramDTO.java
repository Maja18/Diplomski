package ISA.Team22.Domain.DTO;

public class LoaltyProgramDTO {

	private Long id;
    private Integer examinationPoints;
	private Integer consultingPoints;
	private String category;
	private String regularDiscount;
	private String silverDiscount;
	private String goldDiscount;
	private Integer minimumSilverPoints;
	private Integer minimumGoldPoints;
	
	public LoaltyProgramDTO() {
		super();
	}
	
	public LoaltyProgramDTO(Long id, Integer examinationPoints, Integer consultingPoints, String category,
			String regularDiscount, String silverDiscount, String goldDiscount,
			Integer minimumSilverPoints, Integer minimumGoldPoints) {
		super();
		this.id = id;
		this.examinationPoints = examinationPoints;
		this.consultingPoints = consultingPoints;
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
