package ISA.Team22.Domain.DTO;

public class ExaminationUpdateDTO {
	
	Long examinationId;
	String examinationInfo;
	
	public ExaminationUpdateDTO() {
		super();
	}
	
	public ExaminationUpdateDTO(Long examinationId, String examinationInfo) {
		super();
		this.examinationId = examinationId;
		this.examinationInfo = examinationInfo;
	}
	
	public Long getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
	public String getExaminationInfo() {
		return examinationInfo;
	}
	public void setExaminationInfo(String examinationInfo) {
		this.examinationInfo = examinationInfo;
	}


}
