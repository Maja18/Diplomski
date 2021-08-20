package ISA.Team22.Domain.DTO;

public class QRCodeDTO {
	
	 private String drugName;
	 private String drugCode;
	 private int amount;
	
	 public QRCodeDTO() {
		super();
	
	 }

	public QRCodeDTO(String drugName, String drugCode, int amount) {
		super();
		this.drugName = drugName;
		this.drugCode = drugCode;
		this.amount = amount;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
