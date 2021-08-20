package ISA.Team22.Domain.Examination;

import java.time.LocalDate;
import java.time.LocalTime;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Pharmacist;

@Entity
public class Counseling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "startDate",  nullable = false)
	private LocalDate startDate;
	
	@Column(name = "startTime",  nullable = false)
	private LocalTime startTime;
	
	@Column(name = "endTime",  nullable = false)
	private LocalTime endTime;
	
	@Column(name = "counseling_price")
	private Double counselingPrice;
	
	@Column(name = "duration",  nullable = false)
	private Long duration;
	
	@Column(name = "diagnosis",  nullable = false)
	private String diagnosis;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacist pharmacist;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	
	@Enumerated(EnumType.ORDINAL)
	private ExaminationStatus counselingStatus;
	
	public Counseling() {
		super();
	}
	

	
	public Counseling(LocalDate startDate, LocalTime startTime, LocalTime endTime, Double counselingPrice,
			Long duration, String diagnosis, Pharmacist pharmacist, Patient patient,
			ExaminationStatus counselingStatus) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.counselingPrice = counselingPrice;
		this.duration = duration;
		this.diagnosis = diagnosis;
		this.pharmacist = pharmacist;
		this.patient = patient;
		this.counselingStatus = counselingStatus;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}


	public Double getCounselingPrice() {
		return counselingPrice;
	}
	
	public void setCounselingPrice(Double counselingPrice) {
		this.counselingPrice = counselingPrice;
	}


	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Pharmacist getPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public ExaminationStatus getCounselingStatus() {
		return counselingStatus;
	}
	public void setCounselingStatus(ExaminationStatus counselingStatus) {
		this.counselingStatus = counselingStatus;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
}
