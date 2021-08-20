package ISA.Team22.Domain.DrugManipulation;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ISA.Team22.Domain.Users.Supplier;

@Entity
public class SupplierDrugStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity", nullable = false)
    private Integer quantity;
	
	@Column(name = "reservedQuantity")
    private Integer reservedQuantity;
	
	@Column(name = "name",  nullable = false)
    private String name;
	
	@Column(name = "code", unique = true,  nullable = false)
    private String code;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Supplier supplier;
    
	public SupplierDrugStock() {
		super();
	}

	public SupplierDrugStock(Long id, Integer quantity, Integer reservedQuantity, String name, String code, Supplier supplier) {
		super();
		this.quantity = quantity;
		this.reservedQuantity = reservedQuantity;
		this.name = name;
		this.code = code;
		this.supplier = supplier;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Integer reservedQuantity) {
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

    
    
}
