package ISA.Team22.Domain.DTO;

public class SupplierDrugsDTO {
	private Long id;
    private int quantity;
    private int reservedQuantity;
    private String name;
    private String code;
    
	public SupplierDrugsDTO() {
		super();
	}

	public SupplierDrugsDTO(Long id, int quantity, int reservedQuantity, String name, String code) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.reservedQuantity = reservedQuantity;
		this.name = name;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
	
    
}
