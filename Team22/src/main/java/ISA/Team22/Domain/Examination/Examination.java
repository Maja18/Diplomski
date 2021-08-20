package ISA.Team22.Domain.Examination;

import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Examination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "startDate",  nullable = false)
	private LocalDate startDate;
	
	@Column(name = "startTime",  nullable = false)
	private LocalTime startTime;
	
	@Column(name = "endTime",  nullable = false)
	private LocalTime endTime;
	
	@Column(name = "duration",  nullable = false)
	private Long duration;
	
	@Column(name = "examination_price")
	private Double examinationPrice;
	
	@Enumerated(EnumType.ORDINAL)
	private ExaminationStatus examinationStatus;
	
	@Column(name = "diagnosis",  nullable = false)
	private String diagnosis;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="dermatologist_id", nullable=false)
	private Dermatologist dermatologist;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Patient patient;
	
	 @JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false)
	private Pharmacy pharmacy;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "examination_drug",
    joinColumns = @JoinColumn(name = "examination_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private List<Drug> drugs;
	
	public Examination() {
		super();
	}

	
	public Examination(Long id, LocalDate startDate, LocalTime startTime, LocalTime endTime, Long duration, Double examinationPrice,
			ExaminationStatus examinationStatus, String diagnosis, Dermatologist dermatologist, Patient patient,
			Pharmacy pharmacy, List<Drug> drugs) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.examinationPrice = examinationPrice;
		this.examinationStatus = examinationStatus;
		this.diagnosis = diagnosis;
		this.dermatologist = dermatologist;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.drugs = drugs;
	}


	public Examination( LocalDate startDate, LocalTime startTime, LocalTime endTime, Long duration, Double examinationPrice,
			ExaminationStatus examinationStatus, String diagnosis, Dermatologist dermatologist, Patient patient,
			Pharmacy pharmacy, List<Drug> drugs) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.examinationPrice = examinationPrice;
		this.examinationStatus = examinationStatus;
		this.diagnosis = diagnosis;
		this.dermatologist = dermatologist;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.drugs = drugs;
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


	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Double getExaminationPrice() {
		return examinationPrice;
	}

	public void setExaminationPrice(Double examinationPrice) {
		this.examinationPrice = examinationPrice;
	}

	public ExaminationStatus getExaminationStatus() {
		return examinationStatus;
	}

	public void setExaminationStatus(ExaminationStatus examinationStatus) {
		this.examinationStatus = examinationStatus;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	
}
