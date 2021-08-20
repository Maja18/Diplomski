package ISA.Team22.Domain.DTO;

public class UserInfoComplaintDTO {
	 private String fullName;
	 private String email;
	 private Long userId;
	 
	public UserInfoComplaintDTO() {
		super();
	}

	public UserInfoComplaintDTO(String fullName, String email, Long userId) {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	 
	 

}
