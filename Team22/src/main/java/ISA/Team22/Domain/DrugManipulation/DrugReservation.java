package ISA.Team22.Domain.DrugManipulation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.Users.Patient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DrugReservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resevationId;
	
	@Enumerated(EnumType.ORDINAL)
	private DrugStatus status;
	
	@Column(name = "dueDate",  nullable = false)
	private LocalDate dueDate;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	
	@Column(name = "pharmacyId",  nullable = false)
	private Long pharmacyId;
	
	@Column(name = "drugId",  nullable = false)
	private Long drugId;
	   
	public DrugReservation() {
		super();
	}

	public DrugReservation(Long resevationId, DrugStatus status, LocalDate dueDate, Patient patient, Long pharmacyId,
			Long drugId) {
		super();
		this.resevationId = resevationId;
		this.status = status;
		this.dueDate = dueDate;
		this.patient = patient;
		this.pharmacyId = pharmacyId;
		this.drugId = drugId;
	}

	
	public Long getResevationId() {
		return resevationId;
	}

	public void setResevationId(Long resevationId) {
		this.resevationId = resevationId;
	}

	public DrugStatus getStatus() {
		return status;
	}

	public void setStatus(DrugStatus status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	   
	
	   

}
