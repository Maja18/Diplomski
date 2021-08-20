package ISA.Team22.Domain.DTO;

import java.time.LocalDate;

import ISA.Team22.Domain.DrugManipulation.DrugStatus;

public class DrugReservationDTO {
	
	private Long id;
	private DrugStatus drugStatus;
	private LocalDate dueDate;
	private PatientFrontDTO patient;
	private Long pharmacyId;
	private DrugDTO drugId;
	
	
	public DrugReservationDTO() {
		super();
	}

	public DrugReservationDTO(Long id, DrugStatus drugStatus, LocalDate dueDate, PatientFrontDTO patient,
			Long pharmacyId, DrugDTO drugId) {
		super();
		this.id = id;
		this.drugStatus = drugStatus;
		this.dueDate = dueDate;
		this.patient = patient;
		this.pharmacyId = pharmacyId;
		this.drugId = drugId;
	}
	public DrugReservationDTO(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DrugStatus getDrugStatus() {
		return drugStatus;
	}

	public void setDrugStatus(DrugStatus drugStatus) {
		this.drugStatus = drugStatus;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public PatientFrontDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientFrontDTO patient) {
		this.patient = patient;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public DrugDTO getDrugId() {
		return drugId;
	}

	public void setDrugId(DrugDTO drugId) {
		this.drugId = drugId;
	}

	

}
