package ISA.Team22.Domain.DTO;

public class SupplierDrugUpdateDTO {
	
	private Long drugId;
	
    private int newQuantity;
    
	public SupplierDrugUpdateDTO() {
		super();
	}

	public SupplierDrugUpdateDTO(Long drugId, int newQuantity) {
		super();
		this.drugId = drugId;
		this.newQuantity = newQuantity;
	}

	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}
  
}
