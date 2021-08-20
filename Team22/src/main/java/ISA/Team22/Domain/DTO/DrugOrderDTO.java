package ISA.Team22.Domain.DTO;

import java.time.LocalDate;

import ISA.Team22.Domain.DrugManipulation.DrugForm;
import ISA.Team22.Domain.DrugManipulation.DrugType;

public class DrugOrderDTO {
	private Long id;
    private String name;
    private String code;
    private DrugForm form;
    private DrugType type;
    private Integer amount;
    private String manufacturer;
    private LocalDate date;
    private Integer purshaceOrderId;
    
	public DrugOrderDTO() {
		super();
	}

	public DrugOrderDTO(Long id, String name, String code, DrugForm form, DrugType type, Integer amount, String manufacturer,
			LocalDate date, Integer purshaceOrderId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.form = form;
		this.type = type;
		this.amount = amount;
		this.manufacturer = manufacturer;
		this.date = date;
		this.purshaceOrderId = purshaceOrderId;
	}

	public DrugOrderDTO(Long id, String name, String code, DrugForm form, DrugType type, Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.form = form;
		this.type = type;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	

	public DrugForm getForm() {
		return form;
	}

	public void setForm(DrugForm form) {
		this.form = form;
	}

	public DrugType getType() {
		return type;
	}

	public void setType(DrugType type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getPurshaceOrderId() {
		return purshaceOrderId;
	}

	public void setPurshaceOrderId(Integer purshaceOrderId) {
		this.purshaceOrderId = purshaceOrderId;
	}
    
    
}
