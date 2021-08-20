package ISA.Team22.Domain.DTO;

public class PersonBasicInformationDTO {
	private String fullName;
    private String email;
    private Integer userId;
    
	public PersonBasicInformationDTO() {
		super();
	}
	public PersonBasicInformationDTO(String fullName, String email, Integer userId) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    
	

}
