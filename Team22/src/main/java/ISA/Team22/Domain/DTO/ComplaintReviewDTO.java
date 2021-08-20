package ISA.Team22.Domain.DTO;

public class ComplaintReviewDTO {
	
	private Long id;
	private String description;
	private String answer;
	private Boolean isAnswered;
	private String nameFrom;
	private String nameTo;
	
	public ComplaintReviewDTO() {
		super();
	}

	

	public ComplaintReviewDTO(Long id, String description, String answer, Boolean isAnswered, String nameFrom,
			String nameTo) {
		super();
		this.id = id;
		this.description = description;
		this.answer = answer;
		this.isAnswered = isAnswered;
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Boolean getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}



	public String getNameFrom() {
		return nameFrom;
	}



	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}



	public String getNameTo() {
		return nameTo;
	}



	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	

}
